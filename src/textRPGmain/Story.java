package textRPGmain;

//어떤 기능을 수행하는 클래스는 아니다. 스토리의 부분들을 저장 및 출력하기 위한 메소드들을 담고 있다.
public class Story {
	public static void printIntro() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("스토리");
		GameLogic.printSeperator(20);
		System.out.println("스토리 설명1");
		System.out.println("스토리 설명2");
		System.out.println("\n스토리 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_1() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오1");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_1e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오1 끝맺음");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_2() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오2");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_2e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오2 끝맺음");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_3() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오3");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_3e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오3 끝맺음");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_4() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오4");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_4e() {
		GameLogic.clearConsole();
		GameLogic.printSeperator(20);
		System.out.println("메인 시나리오4 끝");
		GameLogic.printSeperator(20);
		System.out.println("시나리오 설명1");
		System.out.println("시나리오 설명2");
		System.out.println("\n시나리오 설명3");
		GameLogic.anythingToContinue();
	}
	
	public static void Mainscenario_E(Player player) {
		GameLogic.clearConsole();
		GameLogic.printSeperator(30);
		System.out.println("당신은 마왕을 무찌르고 세계를 구했습니다!");
		GameLogic.printSeperator(30);
		System.out.println(player.name+"의 이름은 영웅으로서 대대로 전해질 것입니다.");
		System.out.println("\n\t\t THE END\t\t");
		GameLogic.anythingToContinue();
	}
	
}
