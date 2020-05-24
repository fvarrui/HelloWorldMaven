package io.github.fvarrui.helloworld;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("serial")
public class HelloWorldFrame extends JFrame {

	public HelloWorldFrame() {
		super("Hello World");
		initFrame();
        initContent();
        setVisible(true);
	}

	public void initContent() {
		JLabel contentLabel = new JLabel("Hello World");

		JLabel pathLabel = new JLabel("Hello World");
        
		File info = new File("info.txt");
		pathLabel.setText(info.getAbsolutePath());

		try {
			String content = FileUtils.readFileToString(info, Charset.forName("UTF-8"));
			contentLabel.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        getContentPane().add(contentLabel);
        getContentPane().add(pathLabel);
	}

	public void initFrame() {
		setSize(320, 200);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		System.out.println("Starting app ... ");
		System.out.println("PATH=" + System.getenv("PATH"));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HelloWorldFrame();
            }
        });
	}
	
}
