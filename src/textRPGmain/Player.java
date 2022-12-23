package textRPGmain;

public class Player extends Character {

	// 업그레이드/스킬의 각 단계를 저장할 정수 목록
	public int numAtkUpgrades, numDefUpgrades;
	
	// 추가 플레이어 스텟
	int gold, restsLeft, pots;
	
	//스킬명 저장을 위한 배열
	public String[] atkUpgrades={"강인함","힘","무력","권능"};
	public String[] defUpgrades={"통뼈","바위피부","비늘 갑옷","신성한 오라"};
	
	// 플레이어 특정 생성자
	public Player(String name) {
		//슈퍼 클래스의 호출 생성자
		super(name, 100,0);
		//업그레이드를 0으로 세팅
		this.numAtkUpgrades=0;
		this.numDefUpgrades=0;
		// 추가 스텟 설정
		this.gold=5;
		this.restsLeft=1;
		this.pots=0;
		//플레이어가 새 캐릭터를 만들 때 특성을 선택하도록 하기
		chooseTrait();
	}
	
	// 플레이어 특정 메소드
	@Override
	public int attack() {
		return(int)(Math.random()*(xp/4+numAtkUpgrades*3+3)+ xp/10+numAtkUpgrades*2+numDefUpgrades+1);
	}

	@Override
	public int defend() {
		return(int)(Math.random()*(xp/4+numDefUpgrades*3+3)+ xp/10+numDefUpgrades*2+numAtkUpgrades+1);
	}
	
	//플레이어가 스킬 계열을 선택하도록 하기
	public void chooseTrait() {
		GameLogic.clearConsole();
		GameLogic.printHeading("특성을 선택하세요: ");
		System.out.println("(1) "+atkUpgrades[numAtkUpgrades]);
		System.out.println("(2) "+defUpgrades[numDefUpgrades]);
		int input=GameLogic.readInt(">>>",2);
		GameLogic.clearConsole();
		
		//두 가지 케이스
		if(input==1) {
			GameLogic.printHeading(atkUpgrades[numAtkUpgrades]+"를 선택했습니다.");
			numAtkUpgrades++;
		}else{
			GameLogic.printHeading(defUpgrades[numDefUpgrades]+"를 선택했습니다.");
			numDefUpgrades++;
		}
		GameLogic.anythingToContinue();
	}

}
