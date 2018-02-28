/**
* HighScoreWindow.java - Displays a popup High Score List.
*
* @author Brian Etzel
* @version 1.0
*/

import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class HighScoreWindow
{
	/**
	* Shows High Scores
	*
	* @param scoreList - LinkedList of scores to be displayed
	* @return boolean value indicating if a rematch is desired
	*/
	public boolean showHighScores(LinkedList<HighScore> scoreList)
	{
		boolean rematch = false;
		String title = "High Scores";
		String display = "<html><body>High Scores!";
		
		Iterator<HighScore> itr = scoreList.iterator();
		
		int i = 0;
		while (i < 10){
			if (itr.hasNext()){
				display += "<br>" + (i + 1) + ": " + itr.next().toString();
				
			}
			i++;
		}
		
		display += "</body></html>";
		
		Object[] options1 = { "Rematch?",
                "Exit Game" };

        JPanel panel = new JPanel();
        panel.add(new JLabel(display));

        int accepted = JOptionPane.showOptionDialog(null, panel, "High Scores!",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (accepted == JOptionPane.YES_OPTION){
			rematch = true;
        }
		else if (accepted == JOptionPane.NO_OPTION){
			System.exit(0);
		}
		
		return rematch;
	}
}