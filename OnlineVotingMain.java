import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class User
{
	String name;
	boolean voted;
	long ph;
	User(String n,long p)
	{
		name = n;
		ph = p;
		voted = false;
	}
}

class Candidate
{
	String party,name;
	long ph;
	Candidate(String n,long p,String pt)
	{
		name = n;
		party = pt;
		ph = p;
	}
}

class Global
{
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Candidate> candidates = new ArrayList<Candidate>();
	public static int in = 0,vc[],vote = 0;
	public static int searchUser(String n,long p)
	{
		int i;
		for(i=0;i < users.size();i++)
		{
			if(users.get(i).name.equalsIgnoreCase(n) && users.get(i).ph == p)
			{
				in=i;
				return in;
			}
		}
		return -2;
	}
	public static int searchCandidate(String n,long p,String pt)
	{
		int i=0;
		for(i=0;i < candidates.size();i++)
		{
			if(candidates.get(i).name.equalsIgnoreCase(n))
			break;
		}
		if(i < candidates.size())
		{
			if(candidates.get(i).ph == p && candidates.get(i).party.toLowerCase() == pt.toLowerCase())
			return i;
			else
			return -1;
		}
		else
		return -2;
	}
	public static boolean isInteger(String str)
	{
        	return str.chars().allMatch(Character::isDigit);
	}
}

class Voting implements ActionListener
{
	JFrame f;
	JLabel lt,ln,lp;
	JTextField tfn,tfp;
	JRadioButton[] rb;
	ButtonGroup bg;
	JButton bb,bs;
	Voting()
	{
		f = new JFrame("Voting");
		f.setSize(500,500);
		f.setLayout(null);
		Font ft = new Font("Times New Roman",Font.BOLD,30);
		lt = new JLabel("Voting");
		lt.setBounds(175,20,150,35);
		lt.setFont(ft);
		ln = new JLabel("Enter the Name");
		ln.setBounds(75,100,150,30);
		lp = new JLabel("Enter the Phone Number");
		lp.setBounds(75,150,150,30);
		tfn = new JTextField();
		tfn.setBounds(275,100,100,30);
		tfp = new JTextField();
		tfp.setBounds(275,150,100,30);
		bb = new JButton("Back");
		bb.setBounds(10,20,70,30);
		bb.addActionListener(this);
		rb = new JRadioButton[Global.candidates.size()];
		bg = new ButtonGroup();
		Global.vc = new int[Global.candidates.size()];
		for(int i=0;i<Global.candidates.size();i++)
		{
			rb[i] = new JRadioButton(Global.candidates.get(i).party);
			rb[i].setBounds(200,200+(i*30),100,30);
			bg.add(rb[i]);
			f.add(rb[i]);
			//Global.vc[i] = 0;
		}
		bs = new JButton("Submit");
		bs.setBounds(200,400,100,30);
		bs.addActionListener(this);
		f.add(lt);
		f.add(ln);
		f.add(lp);
		f.add(tfn);
		f.add(tfp);
		f.add(bb);
		f.add(bs);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Submit"))
		{
			if(tfp.getText().length() == 10 && Global.isInteger(tfp.getText()))
			{
				int n = Global.searchUser(tfn.getText(),Long.parseLong(tfp.getText()));
				if(n >= 0)
				{
					if(Global.users.get(Global.in).voted == false)
					{
						Global.users.get(Global.in).voted = true;
						for(int i=0;i<rb.length;i++)
						{
							if(rb[i].isSelected())
							Global.vc[i]+=1;
						}
						Global.vote++;
						JOptionPane.showMessageDialog(f,"You polled successfully","Confirmation",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					JOptionPane.showMessageDialog(f,"You have already polled","Confirmation",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(f,"User does not exist","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			JOptionPane.showMessageDialog(f,"Invalid Mobile Number","Error",JOptionPane.ERROR_MESSAGE);
			tfn.setText("");
			tfp.setText("");
			bg.clearSelection();
		}
		else if(ae.getActionCommand().equals("Back"))
		{
			f.dispose();
		}
	}
}

class CandidateReg implements ActionListener
{
	JFrame f;
	JLabel lt,ln,ld,lp,lpt;
	JTextField tfn,tfd,tfp,tfpt;
	JButton bb,bs;
	CandidateReg()
	{
		f = new JFrame("Candidate Registration");
		f.setSize(500,500);
		f.setLayout(null);
		Font ft = new Font("Times New Roman",Font.BOLD,30);
		lt = new JLabel("Candidate Registration");
		lt.setBounds(100,20,350,35);
		lt.setFont(ft);
		ln = new JLabel("Enter the Name");
		ln.setBounds(75,100,150,30);
		ld = new JLabel("Enter the DOB(ddmmyyyy)");
		ld.setBounds(75,150,150,30);
		lp = new JLabel("Enter the Phone Number");
		lp.setBounds(75,200,150,30);
		lpt = new JLabel("Enter the Party");
		lpt.setBounds(75,250,150,30);
		tfn = new JTextField();
		tfn.setBounds(275,100,100,30);
		tfd = new JTextField();
		tfd.setBounds(275,150,100,30);
		tfp = new JTextField();
		tfp.setBounds(275,200,100,30);
		tfpt = new JTextField();
		tfpt.setBounds(275,250,100,30);
		bb = new JButton("Back");
		bb.setBounds(10,20,70,30);
		bb.addActionListener(this);
		bs = new JButton("Submit");
		bs.setBounds(200,300,100,30);
		bs.addActionListener(this);
		f.add(lt);
		f.add(ln);
		f.add(ld);
		f.add(lp);
		f.add(lpt);
		f.add(tfn);
		f.add(tfd);
		f.add(tfp);
		f.add(tfpt);
		f.add(bb);
		f.add(bs);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Submit"))
		{
			if(tfp.getText().length() == 10 && Global.isInteger(tfp.getText()))
			{
				if(tfd.getText().length() == 8 && Global.isInteger(tfd.getText()))
				{
					if((2024-Integer.parseInt(tfd.getText().substring(4))) >= 18)
					{
						if(Global.searchCandidate(tfn.getText(),Long.parseLong(tfp.getText()),tfpt.getText()) == -2)
						{
							Global.candidates.add(new Candidate(tfn.getText(),Long.parseLong(tfp.getText()),tfpt.getText()));
							if(Global.searchUser(tfn.getText(),Long.parseLong(tfp.getText())) == -2)
							Global.users.add(new User(tfn.getText(),Long.parseLong(tfp.getText())));
							JOptionPane.showMessageDialog(f,"Candidate Registered Succussfully","Confirmation",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						JOptionPane.showMessageDialog(f,"Candidate Already Registered","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					JOptionPane.showMessageDialog(f,"Candidate is Under 18 years","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				JOptionPane.showMessageDialog(f,"Invalid Date of Birth","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			JOptionPane.showMessageDialog(f,"Invalid Mobile Number","Error",JOptionPane.ERROR_MESSAGE);
			tfn.setText("");
			tfd.setText("");
			tfp.setText("");
			tfpt.setText("");
		}
		else if(ae.getActionCommand().equals("Back"))
		{
			f.dispose();
		}
	}
}

class UserReg implements ActionListener
{
	JFrame f;
	JLabel lt,ln,ld,lp;
	JTextField tfn,tfd,tfp;
	JButton bb,bs;
	UserReg()
	{
		f = new JFrame("User Registration");
		f.setSize(500,500);
		f.setLayout(null);
		Font ft = new Font("Times New Roman",Font.BOLD,30);
		lt = new JLabel("User Registration");
		lt.setBounds(125,20,250,35);
		lt.setFont(ft);
		ln = new JLabel("Enter the Name");
		ln.setBounds(75,100,150,30);
		ld = new JLabel("Enter the DOB(ddmmyyyy)");
		ld.setBounds(75,150,150,30);
		lp = new JLabel("Enter the Phone Number");
		lp.setBounds(75,200,150,30);
		tfn = new JTextField();
		tfn.setBounds(275,100,100,30);
		tfd = new JTextField();
		tfd.setBounds(275,150,100,30);
		tfp = new JTextField();
		tfp.setBounds(275,200,100,30);
		bb = new JButton("Back");
		bb.setBounds(10,20,70,30);
		bb.addActionListener(this);
		bs = new JButton("Submit");
		bs.setBounds(200,250,100,30);
		bs.addActionListener(this);
		f.add(lt);
		f.add(ln);
		f.add(ld);
		f.add(lp);
		f.add(tfn);
		f.add(tfd);
		f.add(tfp);
		f.add(bb);
		f.add(bs);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("Submit"))
		{
			if(tfp.getText().length() == 10 && Global.isInteger(tfp.getText()))
			{
				if(tfd.getText().length() == 8 && Global.isInteger(tfd.getText()))
				{ 
					if((2024-Integer.parseInt(tfd.getText().substring(4))) >= 18)
					{
						if(Global.searchUser(tfn.getText(),Long.parseLong(tfp.getText())) == -2)
						{
							Global.users.add(new User(tfn.getText(),Long.parseLong(tfp.getText())));
							JOptionPane.showMessageDialog(f,"User Registered Successfully","Confirmation",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						JOptionPane.showMessageDialog(f,"User Already Exist","Error",JOptionPane.ERROR_MESSAGE);
					}
					else
					JOptionPane.showMessageDialog(f,"User is Under 18 years","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				JOptionPane.showMessageDialog(f,"Invalid Date of Birth","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			JOptionPane.showMessageDialog(f,"Invalid Mobile Number","Error",JOptionPane.ERROR_MESSAGE);
			tfn.setText("");
			tfd.setText("");
			tfp.setText("");
		}
		else if(ae.getActionCommand().equals("Back"))
		{
			f.dispose();
		}
	}
}

class Home implements ActionListener
{
	JFrame f;
	JButton bur,bcr,bpv,br;
	JLabel lt;
	Home()
	{
		f = new JFrame("Online Voting");
		f.setLayout(null);
		f.setSize(500,500);
		Font ft = new Font("Times New Roman",Font.BOLD,30);
		lt = new JLabel("Online Voting");
		lt.setFont(ft);
		lt.setBounds(150,20,200,35);
		bur = new JButton("User Registration");
		bur.setBounds(160,100,180,30);
		bur.addActionListener(this);
		bcr = new JButton("Candidate Registration");
		bcr.setBounds(160,150,180,30);
		bcr.addActionListener(this);
		bpv = new JButton("Poll Your Vote");
		bpv.setBounds(160,200,180,30);
		bpv.addActionListener(this);
		br = new JButton("Get Result");
		br.setBounds(160,250,180,30);
		br.addActionListener(this);
		f.add(lt);
		f.add(bur);
		f.add(bcr);
		f.add(bpv);
		f.add(br);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand().equals("User Registration"))
		{
			UserReg ur = new UserReg();
		}
		else if(ae.getActionCommand().equals("Candidate Registration"))
		{
			CandidateReg cr = new CandidateReg();
		}
		else if(ae.getActionCommand().equals("Poll Your Vote"))
		{
			Voting vote = new Voting();
		}
		else if(ae.getActionCommand().equals("Get Result"))
		{
			String out = "";
			int max = 0,smax = -1;
			for(int i=0;i<Global.candidates.size();i++)
			{
				out = out + Global.candidates.get(i).party + " - " + String.valueOf(Global.vc[i]) + " votes\n";
				if(Global.vc[max] < Global.vc[i])
				max = i;
				else if(Global.vc[max] == Global.vc[i] && i != max)
				smax = i;
			}
			if(smax == -1)
			out = out + Global.candidates.get(max).party + " has the majority votes\n";
			else
			out = out + "Result cannot be declared\n";
			out = out + "Votes Polled = " + String.valueOf(Global.vote) + "\nTotal votes in constituency = " + String.valueOf(Global.users.size());
			JOptionPane.showMessageDialog(f,out,"Result",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

class OnlineVotingMain
{
	public static void main(String ar[])
	{
		Home home = new Home();
	}
}