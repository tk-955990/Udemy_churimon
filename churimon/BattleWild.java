package churimon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class BattleWild {

	//フィールド（定数）
	final int ESCAPE_SUCCESS_ST_RATE = 50 ;     //「にげる」成功率の基準値（50%）

	//やせいのモンスターとのバトル
	public void mainBattle () {

		//バトル相手をインスタンス化
		Fushigiyade enemy = new Fushigiyade("やせい","フシギヤデ",20);
		prinTextAsGame("あっ！ やせいの " + enemy.getCharacter() + " が とびだしてきた！");
		//何か入力されるまで待機
		System.out.println("▼");
		pushBtn();

		//自分のモンスターをインスタンス化
		System.out.println("");
		Hitokake myMonster = new Hitokake("ぼく","カケ郎",21);
		prinTextAsGame("ゆけ！ " + myMonster.getName() + "！");
		//何か入力されるまで待機
		System.out.println("▼");
		pushBtn();

		//バトルで使用するローカル変数の定義
		boolean winFlg      = true;    //勝利フラグ（初期値true）
		boolean escapeFlg   = false;   //逃走フラグ（初期値false）
		int turn = 0;                    //ターン数（初期値0）
		int dmg;                         //与えるダメージ
		String cmd;                      //プレイヤーからの入力コマンド
		boolean firstAtkFlg;           //先攻フラグ

		//どちらかのHPゼロになるか「にげる」が成功するまでバトルを繰り返す
		while(myMonster.getHp() > 0 && enemy.getHp() > 0){

			//ターン開始時にローカル変数を初期化
			turn = turn + 1;     //繰り返しのたびにターン数を+1する
			dmg  = 0;
			cmd  = null;
			firstAtkFlg = true;  //先攻フラグ（初期値true）

			//ターン数の表示
			System.out.println("");
			System.out.println("～～～ ターン" + turn + " ～～～");

			//互いのステータスを表示
			System.out.println("");
			System.out.println("あいて：" + enemy.getStatus() );
			System.out.println("こちら：" + myMonster.getStatus() );

			//getCmdメソッドからコマンドを受け取る
			System.out.println("");
			cmd = getCmd(myMonster.getName());

			//先攻・後攻を決定する
			if(myMonster.getSpd() < enemy.getSpd()){
				//相手のモンスターとスピードを比較し、相手が速ければ先攻フラグをfalseに変える
				firstAtkFlg = false;
			}else if (myMonster.getSpd() == enemy.getSpd()){
				//スピードが全く同じであれば50%の確率で先攻フラグをfalseに変える
				firstAtkFlg = judgeFiftyFifty();
			}

			if(firstAtkFlg){
				//自分のモンスターが先攻の場合

				//自分のモンスターの行動
				System.out.println("");
				if(cmd.equals("1")){
					//「たたかう」コマンドの場合

					//相手のモンスターにダメージを与える
					dmg = enemy.damaged(myMonster.useWaza());
					prinTextAsGame(myMonster.getName() + "の " + myMonster.getWazaNm() + " ！");
					prinTextAsGame("  →  " + enemy.getName() + " は " + dmg + " のダメージ を うけた！");

				}else if(cmd.equals("2")){
					//「にげる」コマンドの場合

					//「にげる」が成功した場合は繰り返しを抜け、失敗した場合は「にげられない！」と表示する
					escapeFlg = challengeEscape(myMonster.getSpd() , enemy.getSpd());
					if(escapeFlg){
						break;
					}else{
						System.out.println("");
						prinTextAsGame("にげられない！");
					}
				}
				//何か入力されるまで待機
				System.out.println("▼");
				pushBtn();

				//相手のモンスターの行動(HPがゼロの際は何もしない)
				System.out.println("");
				if(enemy.getHp() > 0){

					//相手のモンスターからダメージを受ける
					dmg = myMonster.damaged(enemy.useWaza());
					prinTextAsGame(enemy.getName() + "の " + enemy.getWazaNm() + " ！");
					prinTextAsGame("  →  " + myMonster.getName() + " は " + dmg + " のダメージ を うけた！");
					//自分のモンスターのHPが0の場合は勝利フラグをfalseに変える
					if(myMonster.getHp()==0){
						winFlg = false;
					}
				}
				//何か入力されるまで待機
				System.out.println("▼");
				pushBtn();

			}else{
				//自分のモンスターが後攻の場合

				//相手のモンスターの行動
				System.out.println("");
				//相手のモンスターからダメージを受ける
				dmg = myMonster.damaged(enemy.useWaza());
				prinTextAsGame(enemy.getName() + "の " + enemy.getWazaNm() + " ！");
				prinTextAsGame("  →  " + myMonster.getName() + " は " + dmg + " のダメージ を うけた！");
				//自分のモンスターのHPが0の場合は勝利フラグをfalseに変える
				if(myMonster.getHp()==0){
					winFlg = false;
				}
				//何か入力されるまで待機
				System.out.println("▼");
				pushBtn();

				//自分のモンスターの行動(HPがゼロの際は何もしない)
				System.out.println("");
				if(myMonster.getHp() > 0){
					if(cmd.equals("1")){
						//「たたかう」コマンドの場合

						//相手のモンスターにダメージを与える
						dmg = enemy.damaged(myMonster.useWaza());
						prinTextAsGame(myMonster.getName() + "の " + myMonster.getWazaNm() + " ！");
						prinTextAsGame("  →  " + enemy.getName() + " は " + dmg + " のダメージ を うけた！");

					}else if(cmd.equals("2")){
						//「にげる」コマンドの場合

						//「にげる」が成功した場合は繰り返しを抜け、失敗した場合は「にげられない！」と表示する
						escapeFlg = challengeEscape(myMonster.getSpd() , enemy.getSpd());
						if(escapeFlg){
							break;
						}else{
							System.out.println("");
							prinTextAsGame("にげられない！");
						}
					}
				}
				//何か入力されるまで待機
				System.out.println("▼");
				pushBtn();
			}
		}

		//バトル結果
		if(escapeFlg){
			System.out.println("");
			prinTextAsGame("うまく にげきれた！");
		}else{
			if(winFlg){
				System.out.println("");
				prinTextAsGame(enemy.getTrainer() + "の " +  enemy.getCharacter() +  " は たおれた！");
			}else{
				System.out.println("");
				prinTextAsGame(myMonster.getTrainer() +  "は めのまえが まっくらに なった！");
			}
		}
		System.out.println("（バトル終了）");
	}

	//戦闘コマンドを受け付ける
	//※内部的に使うメソッドなのでprivate設定にする
	private String getCmd(String monsterName) {
		BufferedReader br = null;
		String cmdin = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			do {
				//
				prinTextAsGame(monsterName + "は どうする？");
				System.out.println("1:たたかう 2:にげる");
				System.out.println("▼");
				cmdin = br.readLine();

				if(!(cmdin.equals("1") || cmdin.equals("2"))){
					System.out.println("[INFO]コマンドが不正です。再入力してください。");
				}
			} while(!(cmdin.equals("1") || cmdin.equals("2")));

		} catch(IOException e) {
			e.printStackTrace();
		}

		return cmdin;
	}

	//何かしらの入力があるまで待機する
	//※内部的に使うメソッドなのでprivate設定にする
	private String pushBtn() {
		BufferedReader br = null;
		String cmdin = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			cmdin = br.readLine();
			System.out.println("");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return cmdin;
	}

	//コマンド「にげる」選択時の処理
	//※内部的に使うメソッドなのでprivate設定にする
	private boolean challengeEscape(int spMy, int spEn) {
		boolean escapeFlag;

		//成功率（100を越えることを許す）
		int success_rate = (int) ESCAPE_SUCCESS_ST_RATE * spMy / spEn ;

		//1～100のランダムな数字を取得
		int rndmNum = 1 + (int)(Math.random() * 100.0);

		//1～100のランダムな数字より成功率の方が高ければ「逃げる」成功
		if(rndmNum < success_rate){
			escapeFlag = true;
		}else{
			escapeFlag = false;
		}
		return escapeFlag;
	}

	//50%の確率でtrueを返す（すばやさが同じだった場合にどちらを先攻とするか決める際に使用）
	//※内部的に使うメソッドなのでprivate設定にする
	private boolean judgeFiftyFifty() {
		boolean judge = true ;

		//0か1をランダムで取得
		int rndmNum = (int)(Math.random() * 2.0);

		if(rndmNum == 0){
			judge = false ;
		}
		return judge;
	}

	//文字をゲームのように表示（50ミリ秒に1文字）
	//※内部的に使うメソッドなのでprivate設定にする
	private void prinTextAsGame(String txt){

		//文字列を配列に1文字ずつセット
		char data[] = txt.toCharArray();
		
		//配列数を取得
		int arr_num = data.length;
		
		for (int i = 0; i < arr_num; i++) {
			
			try{
				//指定ミリ秒の間眠る
				Thread.sleep(50);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.print(data[i]);
		}
		System.out.print("\n");
	}
}