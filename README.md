# rafa
## コートの空き状況を取得
　これは一定間隔で実施され、GCP上のDBかなんかに空き状況を更新しに行く。
  変更を検知したらFunctionでひっかけてRogerにjsonを通知する。
## コートの予約を実行(未実装)
　Rogerで予約が実施された場合に場所・コート・予約日・時間を取得し、会員情報などを入力して予約まで行う。
## コートの抽選を申し込む(未実装)
　Rogerで抽選登録をしているコートに定期的に抽選しに行くやつ