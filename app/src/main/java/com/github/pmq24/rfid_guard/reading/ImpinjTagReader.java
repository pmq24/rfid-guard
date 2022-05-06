package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ImpinjTagReader extends TagReader {

    private final ImpinjReader impinjReader;
    private final Set<String> detectedTags;

    public ImpinjTagReader(String ipAddress) throws OctaneSdkException {
        impinjReader = new ImpinjReader();
        detectedTags = new HashSet<>();

        System.out.println("Connecting");
        impinjReader.connect(ipAddress);

        Settings settings = impinjReader.queryDefaultSettings();

        ReportConfig report = settings.getReport();
        report.setIncludeAntennaPortNumber(true);
        report.setMode(ReportMode.Individual);

        // The reader can be set into various modes in which reader
        // dynamics are optimized for specific regions and environments.
        // The following mode, AutoSetDenseReader, monitors RF noise and interference and then automatically
        // and continuously optimizes the reader's configuration
        settings.setReaderMode(ReaderMode.AutoSetDenseReader);

        // set some special settings for antenna 1
        AntennaConfigGroup antennas = settings.getAntennas();
        antennas.disableAll();
        antennas.enableById(new short[]{1});
        antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
        antennas.getAntenna((short) 1).setIsMaxTxPower(false);
        antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
        antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

        impinjReader.setTagReportListener((impinjReader, tagReport) -> {
            for (Tag tag : tagReport.getTags()) {
                final String hash = tag.getEpc().toHexString() + tag.getFirstSeenTime().ToString();
                if (!detectedTags.contains(hash)) {

                    detectedTags.add(hash);

                    final TagRead tagRead = TagRead.builder()
                            .tagRfid(tag.getEpc().toHexString())
                            .time(LocalDateTime.now())
                            .build();

                    tagReadListener.onRead(tagRead);
                }
            }
        });

        System.out.println("Applying Settings");
        impinjReader.applySettings(settings);
    }
    @Override
    public void start() throws OctaneSdkException {
        impinjReader.start();
    }

    @Override
    public void stop() throws OctaneSdkException {
        impinjReader.stop();
    }
}
