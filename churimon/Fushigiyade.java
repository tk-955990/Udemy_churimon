package churimon;

public class Fushigiyade extends Monster3 {

	// コンストラクタ１（引数なし）
	Fushigiyade() {
		super();
		super.character = "フシギヤデ" ;
	}

	// コンストラクタ２（引数２つ：トレーナー,名前）
	Fushigiyade(String traNm , String Nm) {
		super(traNm , Nm);
		super.character = "フシギヤデ" ;
	}

	// コンストラクタ３（引数３つ：トレーナー,名前,初期レベル）
	Fushigiyade(String traNm , String Nm , int stLv) {
		super(traNm , Nm , stLv);
		super.character = "フシギヤデ" ;
	}

	@Override
	public void levelUp (int upLv) {

		this.lv    += (upLv *  1) ;
		this.hpMax += (upLv * 31) ;
		this.atk   += (upLv *  6) ;
		this.def   += (upLv *  7) ;
		this.spd   += (upLv *  8) ;
		this.hp    = hpMax ;

	}

}
