package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.*;

import java.time.LocalDateTime;

public class ImpinjTagReader extends TagReader {

    private final ImpinjReader reader;

    public ImpinjTagReader(String ipAddress) throws OctaneSdkException {
        reader = new ImpinjReader();

        System.out.println("Connecting");
        reader.connect(ipAddress);

        Settings settings = reader.queryDefaultSettings();

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

        reader.setTagReportListener((impinjReader, tagReport) -> {
            tagReport.getTags().forEach(tag -> {
                tagReadListener.onRead(
                        TagRead.builder()
                                .tagRfid(tag.getEpc().toHexString())
                                .time(LocalDateTime.now())
                                .build()
                );
            });
        });

        System.out.println("Applying Settings");
        reader.applySettings(settings);
    }
    @Override
    public void start() throws OctaneSdkException {
        reader.start();
    }

    @Override
    public void stop() throws OctaneSdkException {
        reader.stop();
    }
}
