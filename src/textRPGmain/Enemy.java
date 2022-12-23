package textRPGmain;

public class Enemy extends Character{

	//플레이어의 현재 경험치를 저장할 변수
	int playerXp;
	
	//적의 특정 생성자
	public Enemy(String name, int playerXp) {
		super(name, (int) (Math.random()*playerXp+playerXp/3+5),(int) (Math.random()*(playerXp/4+2)+1));
		// 변수 할당
		this.playerXp=playerXp;
	}
	
	//적의 특정 공격과 방어 계산
	@Override
	public int attack() {
		return(int)(Math.random()*(playerXp/4+1)+xp/4+3);
	}

	@Override
	public int defend() {
		return(int)(Math.random()*(playerXp/4+1)+xp/4+3);
	}
	

}
