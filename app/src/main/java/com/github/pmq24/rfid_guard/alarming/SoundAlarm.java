package com.github.pmq24.rfid_guard.alarming;

import com.github.pmq24.rfid_guard.database.tag_reads.TagReadRecord;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SoundAlarm implements Alarm {

    public SoundAlarm() {
        alarmFrame = new JAlarmFrame();
    }

    @Override
    public void alarm(TagReadRecord tagRead) {
        alarmFrame.setRfidLabel(tagRead.getTagRfid());
        alarmFrame.setTimeLabel(tagRead.getTime());
        alarmFrame.showGui();
    }

    private final JAlarmFrame alarmFrame;

    private static class JAlarmFrame extends JFrame {

        public JAlarmFrame() {
            super();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            initializeComponents();
            initializeSoundClip();
        }

        public void setRfidLabel(String rfid) {
            rfidLabel.setText(rfid);
        }

        public void setTimeLabel(LocalDateTime time) {
            timeLabel.setText(time.format(DateTimeFormatter.ISO_DATE_TIME));
        }

        public void showGui() {
            EventQueue.invokeLater(() -> setVisible(true));
            soundClip.loop(10);
        }

        public void hideGui() {
            EventQueue.invokeLater(() -> setVisible(false));
            soundClip.stop();
        }

        public void destroy() {
            EventQueue.invokeLater(this::dispose);
        }

        private void initializeComponents() {
            JPanel panel = new JPanel();
            LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(layout);
            panel.setBorder(new EmptyBorder(8,8,8, 8));
            setContentPane(panel);

            JLabel titleLabel = new JLabel("AN UN-PURCHASED ITEM HAS JUST EXITED");
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(titleLabel);

            rfidLabel = new JLabel("xxxx-xxxx-xxxx-xxxx");
            rfidLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(rfidLabel);

            timeLabel = new JLabel("yy:yy");
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(timeLabel);


            closeButton = new JButton("CLOSE");
            closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeButton.addActionListener(e -> {
                hideGui();
                destroy();
            });
            add(closeButton);
        }

        private void initializeSoundClip() {

            AudioInputStream audioInputStream = loadAlarmAudioFile();

            try {
                soundClip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

            try {
                soundClip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }

        private AudioInputStream loadAlarmAudioFile() {
            URL url = getClass().getClassLoader().getResource("alarm-1.wav");
            try {
                return AudioSystem.getAudioInputStream(url);
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private JLabel rfidLabel;
        private JLabel timeLabel;
        private JButton closeButton;

        Clip soundClip;
    }
}
