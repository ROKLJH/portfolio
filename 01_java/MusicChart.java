package day18;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MusicChart extends JFrame implements ActionListener {
	JLabel[] labels;
	JButton bt;
	JLabel log;
	int count = 0;
	
	MusicChart(){
		bt = new JButton("GET!!!");
		bt.setBounds(20, 20, 340, 30);  // x, y, width, height
		add(bt);
		bt.addActionListener(this);
		
		labels = new JLabel[10];
		for(int i=0; i<10; i++) {
			labels[i] = new JLabel("Ranking " + (i+1));
			labels[i].setBounds(20, 80 + (40*i), 340, 30);
			add(labels[i]);
		}
		
		log = new JLabel("Log Region");
		log.setBounds(20, 480, 340, 30);
		add(log);
		
		setSize(400, 600);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusicChart mc = new MusicChart();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt) {
			System.out.println("Button Clicked!!");
			count++;
			log.setText("Button Clicked!! Count : " + count);
		}
		
		String URL = "http://music.bugs.co.kr/chart";
		
		try {
			Document doc = Jsoup.connect(URL).get();
			System.out.println(doc);
			
			Elements elements = doc.select("p.title");  
			// p.title = p태그 중 class가 title인 것
			// CSS Selector 표준 문법 준수
			
			int rank = 1;
			
			for(Element element : elements) {
				String text = "Ranking " + rank + " : " + element.text();
				System.out.println(text);
				labels[rank-1].setText(text);
				if(rank == 10) break;
				rank++;
			}
			
			rank = 1;
			Elements artists = doc.select("p.artist");
			for(Element artist : artists) {
				String text = labels[rank-1].getText(); 
				text +=	"[" + artist.text() + "]";
				labels[rank-1].setText(text);
				if(rank == 10) break;
				rank++;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
