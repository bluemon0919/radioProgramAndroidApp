# radioProgramAndroidApp

ラジオ番組一覧のAndroidアプリです。
[radioProgram](https://github.com/bluemon0919/radioProgramAPI2)のAPIの結果を表示し、Radikoアプリを呼び出します。

## 事前準備

WebAPIを用意します。<br>
本リポジトリで動作確認するためのWebAPIとして、以下をクローンして実行します。<br>
Goがインストールされていることが前提です。<br>

```bash
git clone https://github.com/bluemon0919/radioProgramAPI2.git
cd radioProgramAPI/server
go run main.go
```

## 実行結果

サーバ設定をして実行すると次の通りアプリが起動します。

<img width="250" alt="radioProgram1" src="https://user-images.githubusercontent.com/39006410/90325458-da6f7180-dfb6-11ea-9a7b-2bd7fbf895da.png">

リスト項目をタップするとダイアログが表示されます。

- "聴く"：RadikoのサイトまたはRadikoアプリが起動します。<br>
- "聴き終えた"：リストから該当項目を除外します。


<img width="250" alt="radioProgram2" src="https://user-images.githubusercontent.com/39006410/90325457-d6435400-dfb6-11ea-813e-1dc80ca168e5.png">
