package game;

import java.io.*;

public class SCP_CB {
	static Game game;
	
	private static void saveGame() {
		try {
			FileOutputStream fos = new FileOutputStream("Scp.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(game);
			
			oos.flush();
			oos.close();
			
			System.out.println(" ______________________________\n"
							  +"|                              |\n"
							  +"|                              |\n"
					          +"|          GAME SAVED          |\n"
					          +"|                              |\n"
					          +"|______________________________|\n");
			
			
		} catch (Exception e) {
			System.out.print("Serialization Error! Can't save data.\n"
					+ e.getClass() + ": " + e.getMessage() + "\n");
		}
	}
	
	private static void loadGame() {
		try {
			FileInputStream fis = new FileInputStream("Scp.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			game = (Game) ois.readObject();
			
			ois.close();
			
			System.out.println(" ______________________________\n"
					  		  +"|                              |\n"
					  		  +"|                              |\n"
					  		  +"|          GAME LOADED         |\n"
					  		  +"|                              |\n"
					  		  +"|______________________________|\n");
			
		} catch (Exception e) {
			System.out.print("Serialization Error! Can't load data.\n"
					+e.getClass() + ": " + e.getMessage() + "\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in;
		String input;
		String output;
		
		game = new Game();
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		game.showIntro();
		
		do {
			System.out.print("> ");
			input = in.readLine().trim().toUpperCase();
			output = "";
			switch (input) {
				case "SAVE" :
					saveGame();
					break;
				case "LOAD" :
					loadGame();
					break;
				default :
					output = game.runCommand(input);
					System.out.println(output);
					break;
			}
		} while (!"QUIT".equals(output) && game.IsHeroaLive() && !game.IsVictory());
		
		System.out.println("====================================THE END========================================");
		
		if(!game.IsHeroaLive())
			System.out.println("			 ______________________________\n"
							  +"			|                              |\n"
							  +"			|          GAME OVER           |\n"
							  +"			|       YOUR HERO DIED !       |\n"
							  +"			|   DON'T UNDERESTIMATE THE    |\n"
							  +"			|       STREGNTH OF SCPS       |\n"
							  +"			|    	   NEXT TIME...        |\n"
							  +"			|______________________________|\n");
		
		
		if(game.IsVictory())
			System.out.println("			 ______________________________\n"
							  +"			|                              |\n"
							  +"			|           GAME WIN           |\n"
							  +"			|      YOUR HERO WIN, BUT      |\n"
							  +"			|     IS IT REALLY THE END ?   |\n"
							  +"			|       TO BE CONTINUE...      |\n"
							  +"			|                              |\n"
							  +"			|______________________________|\n");
	}
}
