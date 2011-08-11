
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KFZ extends JFrame {
	private final int XDIM;
	private final int YDIM;
	private JPanel pp, rp, bp;
	private JLabel p1, p2, p1Result, p2Result;
	private JLabel p1Score, p2Score;
	private int p1score, p2score;
	private KeyInput kin;

	private static final String MASTER = "Zoo Sifu";
	private static final String NOVICE = "Zoo Grasshopper";

	//private static final String POSE1_IMG = "kfp_panda.jpg";
	//private static final String POSE2_IMG = "kfp_monkey.jpg";
	//private static final String POSE3_IMG = "kfp_mantis.jpg";
	private static final String POSE1_IMG = "tiger.png";
	private static final String POSE2_IMG = "panda.png";
	private static final String POSE3_IMG = "turtle.png";

	private static final String WINIMG = "win.png";
	private static final String READYIMG = "ready.png";
	//private static final String READYSOUND = "file:///usr/share/sounds/pop.wav";
	//private static final String READYSOUND = "file:///usr/share/games/wesnoth/sounds/bell.wav";
	private static final String READYSOUND = "file:./bell.wav";


	private static final char READY = 'r';
	private static final char RESET = 'c';
	private static Image BACKGROUND = new ImageIcon("KFZ_bond_colours.png").getImage();
	private static Color BGCOLOUR = Color.WHITE;


	private Image background;
	private Image pose1, pose2, pose3;
	private Image win, ready;
	private boolean master = false;
	private String p1Name;
	private Color bgcolour;

	private static int beats(int x, int y) {
		if (Math.abs(x-y) > 1)
			return y - x;
		else
			return x-y;
	}

	private int p1Play(int lastMove) {
		if (master) {
			// Play move that loses to lastMove
			if (lastMove - 1 > 0)
				return lastMove - 1;
			else
				return 3;
		}
		else
			// Play randomly
			return (int) (Math.random()*3) + 1;
	}

	private void setScores() {
		p1Score.setText(p1Name + ": " + p1score);
		p2Score.setText("You: " + p2score);
	}

	private class KeyInput extends KeyAdapter {
		private boolean isReady;
		private int lastPlay = 0;

		public KeyInput() { reset(); }

		public void reset() { isReady = false; }

		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == RESET)
				resetAction();
			else if (isReady) {
				isReady = false;

				//System.out.println(e.getKeyChar());

				int human = e.getKeyChar() - '0';
				if (0 < human && human < 4) {
					// Computer plays
					int computer = p1Play(lastPlay);
					lastPlay = human;

					/*
					p1.setText(""+computer);
					p2.setText(""+human);
					*/
					p1.setIcon( (computer==1) ?
									 new ImageIcon(pose1) :
									( (computer == 2) ? 
										new ImageIcon(pose2) : 
										new ImageIcon(pose3) ) );
	

					// Human play
					p2.setIcon( (human==1) ?
									 new ImageIcon(pose1) :
									( (human == 2) ? 
										new ImageIcon(pose2) : 
										new ImageIcon(pose3) ) );

					if (beats(human,computer) > 0) {
						//p1Result.setBackground(Color.BLACK);
						//p2Result.setBackground(Color.WHITE);
						p1Result.setIcon(null);
						p2Result.setIcon(new ImageIcon(win));
						//p1Result.setText("");
						//p2Result.setText("WIN");
						p2score++;
					}
					else if (beats(human,computer) < 0) {
						//p1Result.setBackground(Color.WHITE);
						//p2Result.setBackground(Color.BLACK);
						p1Result.setIcon(new ImageIcon(win));
						p2Result.setIcon(null);
						//p1Result.setText("WIN");
						//p2Result.setText("");
						p1score++;
					}
					else {
						//p1Result.setBackground(Color.GRAY);
						//p2Result.setBackground(Color.GRAY);
						p1Result.setIcon(null);
						p2Result.setIcon(null);
						//p1Result.setText("");
						//p2Result.setText("");
					}
					setScores();
						
				}
				else
					isReady = true;
			}
			else {
				isReady = e.getKeyChar() == READY;
				if (isReady) {
					p1Result.setIcon(null);
					p2Result.setIcon(new ImageIcon(ready));
					p1.setIcon(null);
					p2.setIcon(null);

					try {
      					java.applet.AudioClip clip =
            					java.applet.Applet.newAudioClip(new java.net.URL(READYSOUND));
      					clip.play();
					} catch (java.net.MalformedURLException murle) {
						murle.printStackTrace();
					}

				}
			}
		}
	}

	public KFZ() { this(1, BGCOLOUR); }
	public KFZ(double s) { this(s, BGCOLOUR); }
	public KFZ(double s, Color bg) {
		// Frame set up
		super();
		XDIM = (int) (1400*s);
		YDIM = (int) (1000*s);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Kung Foo Zoo");
		setSize(new Dimension(XDIM,YDIM));
		background = BACKGROUND;
		bgcolour = bg;

		Font font = new Font("SansSerif", Font.BOLD, (int) (24*s));

		master = false;
		p1Name = NOVICE;

		// LABELS

		// Poses
		int pwidth = XDIM/2-10;
		int pheight = (YDIM*7)/10; //pose1.getHeight(null);
		pose1 = new ImageIcon(POSE1_IMG).getImage().getScaledInstance(pwidth,-1,Image.SCALE_DEFAULT);
		pose2 = new ImageIcon(POSE2_IMG).getImage().getScaledInstance(pwidth,-1,Image.SCALE_DEFAULT);
		pose3 = new ImageIcon(POSE3_IMG).getImage().getScaledInstance(pwidth,-1,Image.SCALE_DEFAULT);
		//pose1 = new ImageIcon(POSE1_IMG).getImage().getScaledInstance(-1,pheight,Image.SCALE_DEFAULT);
		//pose2 = new ImageIcon(POSE2_IMG).getImage().getScaledInstance(-1,pheight,Image.SCALE_DEFAULT);
		//pose3 = new ImageIcon(POSE3_IMG).getImage().getScaledInstance(-1,pheight,Image.SCALE_DEFAULT);

		p1 = new JLabel();
		p1.setPreferredSize(new Dimension(pwidth,pheight));

		p2 = new JLabel();
		p2.setPreferredSize(new Dimension(pwidth,pheight));


		// Scores
		p1score = 0;
		p2score = 0;
		p1Score = new JLabel("", JLabel.CENTER);
		p1Score.setFont(font);
		p2Score = new JLabel("", JLabel.CENTER);
		p2Score.setFont(font);
		setScores();

		// Results
		int rheight = (YDIM-pheight)/2;
		win = new ImageIcon(WINIMG).getImage().getScaledInstance(-1,rheight,Image.SCALE_DEFAULT);
		ready = new ImageIcon(READYIMG).getImage().getScaledInstance(-1,rheight,Image.SCALE_SMOOTH);

		p1Result = new JLabel("", JLabel.CENTER);
		//p1Result.setPreferredSize(new Dimension(250,200));
		p2Result = new JLabel("", JLabel.CENTER);
		//2Result.setPreferredSize(new Dimension(250,200));

		pp = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(background,0,0,getWidth(),getHeight(),null);
			}
		};
		pp.setBackground(bgcolour);

		pp.setLayout(new FlowLayout());
		pp.add(p1);
		pp.add(p2);
		pp.setSize(XDIM,pheight);

		rp = new JPanel();
		rp.setBackground(bgcolour);
		rp.setLayout(new GridLayout(2,2));
		rp.add(p1Result);
		rp.add(p2Result);
		rp.add(p1Score);
		rp.add(p2Score);

		bp = new JPanel();
		bp.setBackground(bgcolour);
		bp.setLayout(new FlowLayout());
		bp.setSize(getContentPane().getWidth(), 30);

		JButton masterButton = new JButton("Master/Novice");
		masterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				master = !master;
				p1Name = (master) ? MASTER : NOVICE;
				setScores();
				pp.requestFocusInWindow();
			}
		});

		JButton startButton = new JButton("start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startAction();
			}
		});
		//startButton.setSize(50,20);

		JButton resetButton = new JButton("reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAction();
			}
		});
		//resetButton.setSize(50,20);

		JButton stopButton = new JButton("stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopAction();
			}
		});
		//stopButton.setSize(50,20);

		bp.add(startButton);
		bp.add(stopButton);
		bp.add(new JLabel("                "));
		bp.add(masterButton);
		bp.add(resetButton);

		getContentPane().add(pp, BorderLayout.NORTH);
		getContentPane().add(rp, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.SOUTH);

		setVisible(true);

		kin = new KeyInput();
		pp.addKeyListener(kin);
	}

	private void startAction() {
		resetAction();
		background = null;
		pp.repaint();
		//pp.requestFocusInWindow();
	}

	private void resetAction() {
		p1score = 0;
		p2score = 0;
		setScores();
		p1.setIcon(null);
		p2.setIcon(null);
		p1Result.setIcon(null);
		p2Result.setIcon(null);
		//p1Result.setBackground(Color.GRAY);
		//p2Result.setBackground(Color.GRAY);
		//p1Result.setText("");
		//p2Result.setText("");
		kin.reset();
		pp.requestFocusInWindow();
	}
	
	private void stopAction() {
		resetAction();
		background = BACKGROUND;
		pp.repaint();
		pp.transferFocus();
	}

	/* MAIN */
	public static void main(String [] args) {
		if (args.length == 0)
			new KFZ();
		else if (args.length == 1)
			new KFZ(Double.parseDouble(args[0]));
		else // must be 4 args
			new KFZ(Double.parseDouble(args[0]), new Color(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])));
	}
}
