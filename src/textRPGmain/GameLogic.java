package textRPGmain;
import java.util.Scanner;

public class GameLogic {
	static Scanner scan=new Scanner(System.in);
	
	static Player player;
	
	public static boolean isRunning;
	
	//랜덤 인카운터
	public static String[] encounters= {"전투","전투","전투","휴식","휴식"};
	//적들
	public static String[] enemies= {"고블린","고블린","오크","슬라임","원소 정령"};
	
	//스토리 요소
	public static int place=0, act=1;
	public static String[] places= {"시초의 대지","종말의 천공"};
			
	//유저 입력을 콘솔에서 받기 위한 메소드
	public static int readInt(String prompt,int userChoices) {
		int input;
		
		do{
			System.out.println(prompt);
			try {
				input=Integer.parseInt(scan.next());
			}catch(Exception e) {
				input= -1;
				System.out.println("정수를 입력하세요");
			}
		}while(input < 1 || input > userChoices);
		return input;
	}
	// (공백 출력하는 방식으로) 콘솔 청소하기
	public static void clearConsole() {
		for(int i=0; i<100; i++) {
			System.out.println();
		}
	}
	// 길이가 n인 문단 구분자 만들기
	public static void printSeperator(int n) {
		for(int i=0;i<n;i++) {
			System.out.print("-");
		}System.out.println();
	}
	// 제목 출력하기
	public static void printHeading(String title) {
		printSeperator(30);
		System.out.println(title);
		printSeperator(30);
	}
	// 플레이어가 누르기 전까지 대기하게 하는 메소드
	public static void anythingToContinue() {
		System.out.println("\n계속하려면 아무 키나 입력하세요...");
		scan.next();
	}
	
	//게임 시작 메소드
	public static void startGame() {
		boolean nameSet = false;
		String name;
		//제목 화면 출력
		clearConsole();
		printSeperator(40);
		printSeperator(30);
		System.out.println("마왕의 시대");
		System.out.println("텍스트 RPG by PYJ");
		printSeperator(30);
		printSeperator(40);
		anythingToContinue();
		
		//플레이어 이름 입력받기
		do {
			clearConsole();
			printHeading("당신의 이름은?");
			name=scan.next();
			//입력받은 이름을 수정할 것인지 묻기
			clearConsole();
			printHeading("입력한 이름["+name+"]가 정확합니까?");
			System.out.println("(1) 그렇다");
			System.out.println("(2) 아니다");
			int input=readInt(">>>",2);
			if(input==1) {
				nameSet=true;
			}
		}while(!nameSet);
		
		// 스토리 인트로 출력
		Story.printIntro();
		
		//해당 이름의 새 플레이어 객체 생성
		player=new Player(name);
		
		// 시나리오 1 출력
		Story.Mainscenario_1();
		
		//게임 루프가 계속되도록 isRunning을 true로 설정함.
		isRunning=true; 
		
		//메인 게임 루프 시작
		gameLoop();
	}
	
	//플레이어의 경험치에 따라 게임 내 변수들을 변경하는 메소드
	public static void checkAct() {
		//xp에 따른 변화
		if(player.xp>=10 && act==1) {
			//act와 장소 값 증가
			act=2;
			place=1;
			//스토리
			Story.Mainscenario_1e();
			//레벨업
			player.chooseTrait();
			//스토리
			Story.Mainscenario_2();
			//적들에게 새로운 값 할당
			enemies[0]="악의 용병";
			enemies[1]="고블린";
			enemies[2]="오크";
			enemies[3]="마왕의 심복";
			enemies[4]="으스스한 이방인";
			//인카운터들에게 새로운 값 할당
			encounters[0]="전투";
			encounters[1]="전투";
			encounters[2]="전투";
			encounters[3]="휴식";
			encounters[4]="상점";
		}else if(player.xp>=50 && act==2){
			//암튼 이런식으로 늘리기...
		}else if(player.xp>=100 && act==3){
			//마지막 전투
			finalBattle();
		}
	}
	//랜덤 인카운터 계산 메소드
	public static void randomEncounter() {
		// 0부터 랜덤 인카운터 배열값 범위의 무작위 수
		int encounter=(int)(Math.random()*encounters.length);
		// 각각의 메소드 호출
		if(encounters[encounter].equals("전투")) {
			randomBattle();
		}else if(encounters[encounter].equals("휴식")) {
			takeRest();
		}else {
			shop();
		}
	}
	
	// 게임 진행(스토리 상의 여정)을 위한 메소드
	public static void continueJourney() {
		//act가 증가해야 할 때 체크하기
		checkAct();
		//게임이 마지막 act에 있지 않을 때 체크하기
		if(act !=4) {
			randomEncounter();	
		}
				
	}
		
	// 캐릭터 스테이터스 오픈
	public static void characterInfo() {
		clearConsole();
		printHeading("캐릭터 정보");
		System.out.println(player.name+"\tHP: "+player.hp+"/"+player.maxhp);
		printSeperator(20);
		//플레이어 경험치와 골드
		System.out.println("XP: "+player.xp+"\t골드: "+player.gold);
		printSeperator(20);
		//소지한 포션
		System.out.println("소지한 포션: "+player.pots);
		printSeperator(20);
		
		//선택한 특성 출력
		if(player.numAtkUpgrades>0) {
			System.out.println("공격 특성: "+player.atkUpgrades[player.numAtkUpgrades -1]);
			printSeperator(20);
		}
		if(player.numDefUpgrades>0) {
			System.out.println("방어 특성: "+player.defUpgrades[player.numDefUpgrades -1]);
			printSeperator(20);
		}
		anythingToContinue();
	}
	
	//쇼핑 / 행상인 조우
	public static void shop() {
		clearConsole();
		printHeading("당신은 로브를 뒤집어쓴 사람과 마주쳤습니다. 그는 당신에게 거래를 제안합니다.");
		int price=(int)(Math.random()*(10+player.pots*3)+10+player.pots);
		System.out.println("- 체력 포션: "+price+" 골드");
		printSeperator(20);
		// 구매할 것인지 물어봄
		System.out.println("하나 구매하시겠습니까?\n(1) 구매한다\n(2) 구매하지 않는다");
		int input=readInt(">>>",2);
		// 확실히 구매할 것인지 물어봄
		if(input==1) {
			clearConsole();
			//충분한 골드를 소지했는지 확인
			if(player.gold>=price) {
				printHeading("체렉 포션 1개를 "+price+"에 구매했습니다.");
				player.pots++;
				player.gold-=price;
			}else {
				printHeading("해당 물품을 구매하기 위한 골드가 부족합니다.");
			}anythingToContinue();
		}
	}
	
	//휴식 취하기
	public static void takeRest() {
		clearConsole();
		if(player.restsLeft>=1) {
			printHeading("휴식을 취하겠습니까? (남은 휴식 포인트: "+player.restsLeft+")");
			System.out.println("(1) 휴식한다\n(2) 하지 않는다");
			int input=readInt(">>>",2);
			if(input==1) {
				// 플레이어가 휴식을 취함
				clearConsole();
				if(player.hp<player.maxhp) {
					int hpRestored=(int)(Math.random()*(player.xp/4+1)+10);
					player.hp+=hpRestored;
					if(player.hp>player.maxhp) {
						player.hp=player.maxhp;
					}System.out.println("당신은 푹 쉬었습니다. "+hpRestored+"만큼 회복했습니다.");
					System.out.println("(현재 체력: "+player.hp+"/"+player.maxhp+")");
					player.restsLeft--;
				}else {
					System.out.println("이미 체력이 최대입니다. 지금은 휴식을 취할 필요가 없습니다.");
				anythingToContinue();
				}
			}
		}
	}
	
	// 랜덤 배틀 만들기
	public static void randomBattle() {
		clearConsole();
		printHeading("당신은 마물과 마주쳤다. 당신은 이에 맞서 싸워야 한다!");
		anythingToContinue();
		//랜덤한 이름의 적 생성
		battle(new Enemy(enemies[(int)(Math.random()*enemies.length)],player.xp));
	}
	
	// 메인 전투 메소드
	public static void battle(Enemy enemy) {
		//메인 전투 루프
		while(true) {
			clearConsole();
			printHeading(enemy.name+"\nHP"+enemy.hp+"/"+enemy.maxhp);
			printHeading(player.name+"\nHP"+player.hp+"/"+player.maxhp);
			System.out.println("무엇을 할까?");
			printSeperator(20);
			System.out.println("(1) 싸운다\n(2) 포션 사용\n(3) 도망치기");
			int input=readInt(">>>",3);
			//플레이어의 선택에 따른 실행
			if(input==1) {
				//전투
				//데미지 계산
				int dmg=player.attack()-enemy.defend();
				int dmgTook=enemy.attack()-player.defend();
				//데미지 값이 음수가 아닌지 확인
				if(dmgTook<0) {
					//플레이어의 방어력이 상대 공격력을 초과했을 경우 반사 데미지 추가(유희왕??)
					dmg-=dmgTook/2;	//-(음수)이므로 이것은 데미지가 더해지는 것임.
					dmgTook=0;
				}if(dmg<0) {
					dmg=0;
				}
				//데미지 수치 적용(HP 변동)
				player.hp-=dmgTook;
				enemy.hp-=dmg;
				//해당 배틀 스텝의 처리 결과 출력
				clearConsole();
				printHeading("전투");
				System.out.println("당신의 공격으로 "+enemy.name+"에게 "+dmg+"의 피해를 입렸습니다.");
				printSeperator(20);
				System.out.println(enemy.name+"의 공격으로 "+dmgTook+"의 피해를 입었습니다.");
				anythingToContinue();
				//플레이어의 생존 여부 확인
				if(player.hp<=0) {
					playerDied();
					break;
				}else if(enemy.hp<=0) {
					//플레이어 승리
					clearConsole();
					printHeading(enemy.name+"을(를) 쓰러뜨렸습니다!");
					//경험치 획득
					player.xp+=enemy.xp;
					System.out.println(enemy.xp+"의 경험치를 얻었습니다.");
					//랜덤 드롭
					boolean addRest=(Math.random()*5 + 1 <= 2.25);
					int goldEarned=(int)(Math.random()*enemy.xp);
					if(addRest) {
						player.restsLeft++;
						System.out.println("휴식 포인트를 얻었습니다.");
					}
					if(goldEarned>0) {
						//유튜버가 실수했나? 골드를 안 주길래 만듦.
						player.gold+=goldEarned;
						System.out.println(enemy.name+"에게서 "+goldEarned+"의 골드를 얻었습니다.");
					}
					anythingToContinue();
					break;
				}
			}else if(input==2){
				//포션 사용
				if(player.pots>0 && player.hp<player.maxhp) {
					//포션 마시기가 가능한 경우
					//포션을 사용할 것인지 물음
					printHeading("포션을 마시겠습니까? ("+player.pots+"병 소지)");
					System.out.println("(1) 마신다\n(2) 마시지 않는다");
					input=readInt(">>>",2);
					if(input==1) {
						//플레이어가 포션을 마심
						player.hp=player.maxhp;
						clearConsole();
						printHeading("포션을 마셨습니다. 최대 체력("+player.maxhp+")을 회복합니다.");
						anythingToContinue();
					}		
				}else {
					//포션 마시기가 불가능한 경우
					System.out.println("소지한 포션이 없거나 최대 체력입니다.");
					anythingToContinue();
				}
			}else{
				//도망치기
				clearConsole();
				//플레이어가 마지막 act(최종 전투)에 있는지 체크하기
				if(act!=4) {
				// 약 65%의 확률로 도주 성공
				if(Math.random()*10+1>=3.5) {
					printHeading(enemy.name+"으로부터 무사히 도망쳤습니다!");
					anythingToContinue();
					break;
				}else {
					printHeading(enemy.name+"은(는) 끈질기게 당신을 추격합니다!");
					int dmgTook=enemy.attack();
					//데미지 수치 적용(HP 변동)
					player.hp-=dmgTook;
					System.out.println(enemy.name+"의 공격으로 "+dmgTook+"의 피해를 입었습니다.");
					anythingToContinue();
					if(player.hp<=0) {
						playerDied();
						break;
					}
				}
				}else {
					printHeading("적에게서 벗어날 수 없는 상황입니다. 여기서 반드시 결착을 지어야 합니다!");
					anythingToContinue();
				}
				
				
			}
		}
	}
	
	// 메인 메뉴 출력
	public static void printMenu() {
		clearConsole();
		printHeading(places[place]);
		System.out.println("무엇을 할까?");
		printSeperator(20);
		System.out.println("(1)계속하기");
		System.out.println("(2)스테이터스");	
		System.out.println("(3)게임 종료");	
	}
	
	// 게임의 최종 전투
	public static void finalBattle() {
		//마왕을 생성하고 전투에 돌입하기
		battle(new Enemy("마왕",300));
		//적절한 엔딩 출력
		Story.Mainscenario_E(player);
		isRunning=false;
	}
	
	// 플레이어 사망 시 호출할 메소드
	public static void playerDied() {
		clearConsole();
		printHeading("당신은 끝내 쓰러졌습니다. 알 수 없는 공간 저편에서 밝은 빛이 보입니다...");
		printHeading("당신은 이번 여정에서의 누적 경험치는 "+player.xp+"입니다.");
		System.out.println("플레이해주셔서 감사합니다 :D");
		isRunning=false;
	}
	
	// 메인 게임 루프
	public static void gameLoop() {
		while(isRunning) {
			printMenu();
			int input=readInt(">>>",3);
			if(input==1) {
				continueJourney();
			}else if(input==2){
				characterInfo();
			}else {
				isRunning=false;
			}
		}
	}
}
