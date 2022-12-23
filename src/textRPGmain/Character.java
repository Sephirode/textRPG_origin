package textRPGmain;

public abstract class Character {
	public String name;
	public int maxhp,hp,xp;
	
	//캐릭터 생성자
	public Character(String name,int maxhp,int xp) {
		this.name=name;
		this.maxhp=maxhp;
		this.xp=xp;
		this.hp=maxhp;
	}
	
	//모든 캐릭터의 공통 메소드
	public abstract int attack();
	public abstract int defend();
}
