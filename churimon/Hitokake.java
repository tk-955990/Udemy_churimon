package churimon;

public class Hitokake extends Monster3 {
	
	// コンストラクタ１（引数なし）
	Hitokake() {
		super();
		super.character = "ヒトカケ" ;
	}
	
	// コンストラクタ２（引数２つ：トレーナー,名前）
	Hitokake(String traNm , String Nm) {
		super(traNm , Nm);
		super.character = "ヒトカケ" ;
	}
 
	// コンストラクタ３（引数３つ：トレーナー,名前,初期レベル）
	Hitokake(String traNm , String Nm , int stLv) {
		super(traNm , Nm , stLv);
		super.character = "ヒトカケ" ;
	}
	
	@Override
    public void levelUp (int upLv) {
		
		this.lv    += (upLv *  1) ;
		this.hpMax += (upLv * 29) ;
		this.atk   += (upLv *  8) ;
		this.def   += (upLv *  5) ;
		this.spd   += (upLv *  9) ;
		this.hp    = hpMax ;
		
	}
	
	
	

}
