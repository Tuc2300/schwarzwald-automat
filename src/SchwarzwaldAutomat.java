package grundfunktionen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AutomatenGrundfunktionen {

public static void main(String[] args) throws IOException {


		Scanner sc = new Scanner(System.in);

		final String SEPERATIONLINE = "------------------------------------------------------------------";
		final String DATAPATHPRODUCTS = "C:/Users/FKretzschmar/Documents/GitHub/schwarzwald-automat/storage/products.txt";
		final String DATAPATHPRICES = "C:/Users/FKretzschmar/Documents/GitHub/schwarzwald-automat/storage/prices.txt";

		final int MAINTENANCE = 736493;

		final char CURRENCY = '€';

		double productListPrice;

		int change;
		int currentUserMoney = 0;
		int missingMoney;
		int moneyInput = 0;
		int selectionNumber;
		int internalSelectionNumber;
		int newPrice;
		int itemNumber;
	
		double changeDouble;
		double missingMoneyDouble;

		boolean loop = true;
		boolean machineLoop = true;

		while(machineLoop == true)
		{
			File productFile = new File(DATAPATHPRODUCTS);
			String[] products = new String[12];
			int productCount = 0;
			
			Scanner fileScanner = new Scanner(productFile);
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				if (!line.trim().isEmpty()) {
					products[productCount] = line;
					productCount++;
				}
			}
			fileScanner.close();
			
			File priceFile = new File(DATAPATHPRICES);
			int[] prices = new int[12];
			int priceCount = 0;
			
			fileScanner = new Scanner(priceFile);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine().trim();
				if (!line.isEmpty()) {
					prices[priceCount] = Integer.parseInt(line);
					priceCount++;
				}
			}
			fileScanner.close();

			System.out.print(
				SEPERATIONLINE + "\n" +
				"Schwarzwaldautomat" + "\n" +
				SEPERATIONLINE + "\n" +
				"Zur Auswahl stehen Ihnen zur Zeit:" + "\n"
			);

			for (int i = 0; i < productCount; i++) {
				productListPrice = prices[i] / 100.0;
				System.out.print("[" + (i+1) + "] " + products[i] + " " + productListPrice + CURRENCY + "\n");
			}		

			System.out.println(
				SEPERATIONLINE + "\n" +
				"Bitte geben Sie eine der angezeigten Nummern ein, um ihre Auswahl zu treffen." + "\n" +
				SEPERATIONLINE
			);
			do 
			{
				selectionNumber = sc.nextInt();

				internalSelectionNumber = selectionNumber - 1;

				if(selectionNumber != MAINTENANCE && selectionNumber > productCount || internalSelectionNumber < 0)
				{
					System.out.println(
						SEPERATIONLINE + "\n" +
						"Leider bieten wir zurzeit kein Produkt mit dieser Nummer an bitte entscheiden Sie sich für ein anderes Produkt."
					);
					break;
				}
				else
				{
					switch (selectionNumber) {
						case MAINTENANCE:
							System.out.println(
								SEPERATIONLINE + "\n" +
								"Wartungsidenfikationsnummer erkannt Programm wird heruntergefahren."
							);
							loop = false;
							break;
					
						default:
							loop = false;
							break;
					}
				}

			} while(loop == true);

			if(selectionNumber != MAINTENANCE && internalSelectionNumber >= 0 && selectionNumber <= productCount)
			{

				productListPrice = prices[internalSelectionNumber] / 100.0;
				System.out.println(
					SEPERATIONLINE + "\n" +
					"Sie haben " + products[internalSelectionNumber] + " ausgewält." + "\n" +
					"Bitte werfen Sie jetzt den Geldbetrag in höhe von " + productListPrice + CURRENCY + " ein." + "\n" +
					SEPERATIONLINE
				);
				loop = true;
			}

			if(selectionNumber != MAINTENANCE && internalSelectionNumber >= 0 && selectionNumber <= productCount)
			{
				do
				{
					moneyInput = sc.nextInt();
	
					loop = false;
	
					currentUserMoney += moneyInput;
	
					if(moneyInput != MAINTENANCE && moneyInput > 0)
					{
						missingMoney = prices[internalSelectionNumber] - currentUserMoney;
	
						if(missingMoney <= 0)
						{
							change = currentUserMoney - prices[internalSelectionNumber];
	
							System.out.println(
								SEPERATIONLINE + "\n" +
								"Vielen Dank für Ihren Einkauf!" + "\n" +
								"Unten in der Auslage finden Sie ihr Produkt." + "\n" +
								"Gekauftes Produkt: " + products[internalSelectionNumber]
							);
							if(change > 0)
							{
								changeDouble = change / 100.0;
								System.out.println(
									"Wechselgeld: " + changeDouble + CURRENCY
								);
							}
							else
							{

							}					
						}
						else
						{
							missingMoneyDouble = missingMoney / 100.0;
							System.out.println(
							SEPERATIONLINE + "\n" +
							"Es fehlen noch " + missingMoneyDouble + CURRENCY + "\n" +
							"Bitte werfen Sie entweder den noch fehlenden Geldbetrag ein oder drücken Sie die 0 um den Bezahlvorgang abzubrechen." + "\n" +
							SEPERATIONLINE
							);
							loop = true;
						}
					}
					else if(moneyInput <= 0)
					{
						change = currentUserMoney;
						changeDouble = change /100.0;
						System.out.println(
							SEPERATIONLINE + "\n" +
							"Sie haben den Vorgang abgebrochen Ihr Wechselgeld beträgt: " + changeDouble + CURRENCY
						);
					}
					else if(moneyInput == MAINTENANCE)
					{
						System.out.println(
							SEPERATIONLINE + "\n" +
							"Wartungsidenfikationsnummer erkannt Programm wird heruntergefahren."
						);
					}
				}while(loop == true && selectionNumber != MAINTENANCE);
			}
			
			if(moneyInput != MAINTENANCE && selectionNumber != MAINTENANCE)
			{
				currentUserMoney = 0;
				machineLoop = true;
			}
			else
			{
				loop = true;
				while(loop == true)
				{
					System.out.println(
						SEPERATIONLINE + "\n" +
						"Willkommen im Wartungsmodus zur Auwahl stehen:" + "\n" +
						SEPERATIONLINE + "\n" +
						"[1] Ware im Warenfach austauschen / hinzufügen" + "\n" +
						"[2] Preis einer Ware ändern (in Cent)" + "\n" +
						"[3] Ware im Warenfach entfernen (Ware entnehmen)" + "\n" +
						"[4] Wartungsmodus beenden" + "\n" +
						SEPERATIONLINE 
					);
	
					selectionNumber = sc.nextInt();

					internalSelectionNumber = selectionNumber - 1;
	
					switch (selectionNumber) {
						case 1:

							System.out.println(
							SEPERATIONLINE + "\n" +
							"Geben Sie die Nummer ein welches Produkt sie hinzufügen/ersetzen wollen. " + "\n" +
							SEPERATIONLINE
							);

							selectionNumber = sc.nextInt();

							internalSelectionNumber = selectionNumber - 1;

							if(selectionNumber <= productCount && internalSelectionNumber >= 0)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Aktuelles Produkt: " + products[internalSelectionNumber] + "\n" +
									"Bitte geben Sie den Namen des neuen Produkts ein." + "\n" +
									SEPERATIONLINE
								);
							}
							else
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Bitte geben Sie den Namen des neuen Produkts ein." + "\n" +
									SEPERATIONLINE
								);
							}

							sc.nextLine();

							String newProduct = sc.nextLine();

							if(selectionNumber <= productCount && internalSelectionNumber >= 0)
							{
								products[internalSelectionNumber] = newProduct;
								prices[internalSelectionNumber] = 0;
							}
							else
							{
								products[productCount] = newProduct;
								productCount++;
								prices[priceCount] = 0;
								priceCount++;
							}

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Produkt wurde erfolgreich hinzugefügt/ersetzt!"
							);
							
							break;
						case 2:

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Um einen Preis zu setzen, geben Sie die passende Nummer ein." + "\n" +
								SEPERATIONLINE
							);

							selectionNumber = sc.nextInt();

							internalSelectionNumber = selectionNumber - 1;

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Sie haben die Nummer: " + selectionNumber + " gewählt."
							);

							if(selectionNumber > productCount)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Die gewählte Ware ist nicht im System verfügbar."
								);
								machineLoop = false;
								break;
							}

							if(internalSelectionNumber < 0)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Negativer Wert oder Null nicht erlaubt."
								);
								break;
							}

								System.out.println(
									SEPERATIONLINE + "\n" +
									"Ware: " + products[internalSelectionNumber] + "\n" +
									"Bitte geben Sie den neuen Preis des Produkts ein" + "\n" +
									SEPERATIONLINE
								);

							newPrice = sc.nextInt();

							if(newPrice < 1)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Waren dürfen nicht für 0 oder weniger angeboten werden."
								);
								break;
							}

							if(selectionNumber <= priceCount && internalSelectionNumber >= 0) {
								prices[internalSelectionNumber] = newPrice;
							} else {
								prices[internalSelectionNumber] = newPrice;
							}

							productListPrice = prices[internalSelectionNumber] / 100.0;

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Ware: " + products[internalSelectionNumber] + "\n" +
								"Neuer Preis: " + productListPrice + CURRENCY
							);
							break;
						case 3:
							
							System.out.println(
								SEPERATIONLINE + "\n" +
								"Um ein Produkt zu entfernen, geben Sie die passende Nummer ein." + "\n" +
								SEPERATIONLINE
							);

							selectionNumber = sc.nextInt();

							internalSelectionNumber = selectionNumber - 1;

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Sie haben die Nummer: " + selectionNumber + " gewählt."
							);

							if(selectionNumber > productCount)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Die gewählte Ware ist nicht im System verfügbar"
								);
								machineLoop = false;
								break;
							}

							if(internalSelectionNumber < 0)
							{
								System.out.println(
									SEPERATIONLINE + "\n" +
									"Negativer Wert oder Null nicht erlaubt Programm fährt herunter."
								);
								machineLoop = false;
								break;
							}

							System.out.println(
								SEPERATIONLINE + "\n" +
								"Der Gegenstand: " + products[internalSelectionNumber] + " wurde entfernt." + "\n"
							);

							for(int i = internalSelectionNumber; i < productCount - 1; i++) {
								products[i] = products[i + 1];
								prices[i] = prices[i + 1];
							}
							productCount--;
							priceCount--;

							break;
						case 4:
							System.out.println(
								SEPERATIONLINE + "\n" +
								"Wartungsmodus wird beendet." + "\n" +
								SEPERATIONLINE
							);

							FileWriter writer = new FileWriter(productFile);
							for(int i = 0; i < productCount; i++) {
								writer.write(products[i] + "\n");
							}
							writer.close();

							writer = new FileWriter(priceFile);
							for (int price : prices) {
								writer.write(price + "\n");
							}
							writer.close();

							machineLoop = true;
							loop = false;
							currentUserMoney = 0;
							productListPrice = 0;
							break;
					
						default:
							System.out.println(
								SEPERATIONLINE + "\n" +
								"Unerlaubte Auswahl." + "\n" +
								SEPERATIONLINE
							);
							break;
					}
				}
			}
		}
	}
}