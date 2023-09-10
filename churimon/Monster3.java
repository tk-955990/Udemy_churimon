package churimon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monster3 {

	String character ;  // 種族
	String trainer ;    // トレーナー
	String name ;       // 名前
	int lv  ;           // レベル
	int hp  ;           // HP
	int atk ;           // 攻撃力
	
	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public String getWazaNm() {
		return wazaNm;
	}

	public void setWazaNm(String wazaNm) {
		this.wazaNm = wazaNm;
	}

	public double getWazaDmRate() {
		return wazaDmRate;
	}

	public void setWazaDmRate(double wazaDmRate) {
		this.wazaDmRate = wazaDmRate;
	}

	int def ;           // 防御力 
	int spd ;           // 素早さ
	int hpMax ;         // HP初期値
	String wazaNm  ;    // 技（名前）
	double wazaDmRate ; // 技（ダメージ倍率）

	
	// コンストラクタ１（引数なし）
	Monster3() {
	
		this.character = null ;
		this.trainer   = null ;
		this.name  = null ;
		this.lv    =  1 ;
		this.hp    = 80 ;
		this.atk   = 15 ;
		this.def   = 10 ;
		this.spd   = 10 ;
		this.hpMax = 80 ;
		this.wazaNm     = "たいあたり" ;
		this.wazaDmRate = 1.0 ;
		
	}
	
	// コンストラクタ２（引数２つ）
	Monster3(String Tra , String Nm) {
		
		this.character = null ;
		this.trainer   = Tra ;    // トレーナー
		this.name  = Nm ;         // 名前
		this.lv    =  1 ;
		this.hp    = 80 ;
		this.atk   = 15 ;
		this.def   = 10 ;
		this.spd   = 10 ;
		this.hpMax = 80 ;
		this.wazaNm     = "たいあたり" ;
		this.wazaDmRate = 1.0 ;
		
	}
	
	// コンストラクタ３（引数３つ）
	Monster3(String Tra , String Nm , int startLv) {
		
		this(Tra , Nm);          // コンストラクタ２を呼び出し初期化する
		
		if(startLv > 1) {        // 初期レベルが１よりも大きかった場合
		levelUp(startLv-1) ;
		}
		
	}
	
	
	
    @Override
	public String toString() {
		return "Monster1 [character=" + character + ", trainer=" + trainer + ", name=" + name + ", lv=" + lv + ", hp="
				+ hp + ", atk=" + atk + ", def=" + def + ", spd=" + spd + ", hpMax=" + hpMax + ", wazaNm=" + wazaNm
				+ ", wazaDmRate=" + wazaDmRate + "]";
	}
	
    // レベルアップによるステータス上昇
	public void levelUp (int upwardLevel) {
		
		this.lv    += (upwardLevel *  1) ;
		this.hpMax += (upwardLevel * 30) ;
		this.atk   += (upwardLevel *  5) ;
		this.def   += (upwardLevel *  5) ;
		this.spd   += (upwardLevel *  5) ;
		this.hp    =  hpMax ;
		
	}
	
	// 技に関する情報の設定（技の名前、ダメージ倍率）
	public void setWaza (String waza , String wazaRate) {

		Pattern comparisonPattern = Pattern.compile("^[0-9]+\\.[0-9]$") ;
		Matcher matcher = comparisonPattern.matcher(wazaRate) ;

		if(matcher.matches() ) {

			try {
				this.wazaNm = waza ;
				this.wazaDmRate = Double.parseDouble(wazaRate) ;
			} catch (NumberFormatException e) {
				System.out.println("[ [ERROR] 技の設定に失敗しました  ]");
			}
		}else {
			System.out.println("[ [ERROR] 技の設定に失敗しました  ]");
		}
	}

	// ステータスの表示
	public String getStatus() {

		String monsterStatus ;
		monsterStatus = '【' + this.name + "  Lv:" + this.lv + "  HP: " + this.hp + "/" + this.hpMax+ '】' ;

		return monsterStatus ;

	}

	// 相手に与えるダメージポイントの計算
	public int useWaza() {

		int damagePt ;    // 相手に与えるダメージポイント

		BigDecimal valAt     = BigDecimal.valueOf(atk);
		BigDecimal valDmRate = BigDecimal.valueOf(wazaDmRate);
		
		// 攻撃力 × 技のダメージ倍率
		BigDecimal valDamage = valAt.multiply(valDmRate); 
		BigDecimal valDmPt   = valDamage.setScale(0,RoundingMode.DOWN);

		damagePt = valDmPt.intValue();

		return damagePt ;

	}
	
	// ダメージ減算率での実際に受けるダメージポイントの計算
	public int damaged(int damage) {
		
		int realDamagePt ;   // 実際に受けるダメージポイント
		
		
		BigDecimal valDef = BigDecimal.valueOf(def);
		BigDecimal val120    = new BigDecimal("120");
		BigDecimal SubtractRate ;                // ダメージ減算率
		
		// ダメージ減算率 ＝ 1 / (1 + 防御力 ÷ 120)  ※小数点第三位切り捨て
		SubtractRate = BigDecimal.ONE.divide(BigDecimal.ONE.add(valDef.divide(val120)), 3, RoundingMode.DOWN);

		BigDecimal valDamage = BigDecimal.valueOf(damage);
		
		// ダメージ値 × ダメージ減算率
		BigDecimal valRealDm = valDamage.multiply(SubtractRate);
		 
		realDamagePt = valRealDm.intValue();
		
		if(this.hp > realDamagePt) {
			this.hp -= realDamagePt ;
		}else {
			this.hp = 0 ;
		}
		
		return realDamagePt ;
		
	}

}