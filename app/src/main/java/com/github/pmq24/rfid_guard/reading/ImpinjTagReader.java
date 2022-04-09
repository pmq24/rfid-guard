package com.github.pmq24.rfid_guard.reading;

import com.github.pmq24.rfid_guard.data.TagRead;
import com.impinj.octane.*;

import java.time.LocalDateTime;

public class ImpinjTagReader extends TagReader {

    public ImpinjTagReader(String ipAddress) throws OctaneSdkException {
        connectToDevice(ipAddress);
    }

    @Override
    public void start() {
        try {
            impinjReader.start();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            impinjReader.stop();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
        }
    }

    private void connectToDevice(String ipAddress) throws OctaneSdkException {

        impinjReader = new ImpinjReader();
        impinjReader.connect(ipAddress);

        impinjReader.connect(ipAddress);

        Settings settings = impinjReader.queryDefaultSettings();

        ReportConfig reportConfig = settings.getReport();
        reportConfig.setIncludeAntennaPortNumber(true);
        reportConfig.setMode(ReportMode.Individual);
        settings.setReaderMode(ReaderMode.AutoSetDenseReader);

        AntennaConfigGroup antennas = settings.getAntennas();
        antennas.disableAll();
        antennas.enableById(new short[]{1});
        antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
        antennas.getAntenna((short) 1).setIsMaxTxPower(false);
        antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
        antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

        impinjReader.setTagReportListener((reader, report) -> {
            report.getTags().forEach(tag -> {
                TagRead tagRead = TagRead
                        .builder()
                        .tagRfid(tag.getEpc().toHexString())
                        .time(LocalDateTime.now())
                        .build();

                notifyListener(tagRead);
            });
        });
    }

    private ImpinjReader impinjReader;
}
