Socket.IOサンプルプログラム
===========================

SocketIOによるリアルタイムWebのサンプルです。

環境
----

以下の環境で動作確認しています。

PaaS

* [eXcale](https://www.excale.net)
* [dotCloud](https://www.dotcloud.com)

サーバ側

* [Node.js](http://nodejs.org) v0.8.22
* [express](http://expressjs.com) v3.1.0
* [socket.io](http://socket.io) v0.9.13

クライアント側（ブラウザ）

* Mac OS X Lion v10.7.4
* Google Chrome v25.0.1364.172
* Firefox 19.0.2

クライアント側（iOS）

* Mac OS X Lion v10.7.4
* iPad 3rd(MC705J)
* iOS v6.0.1(10A523)
* XCode v4.5.2
* [AZSocketIO](https://github.com/pashields/AZSocketIO) v0.0.4
* [SocketRocket](https://github.com/square/SocketRocket) v0.2.0
* [AFNetworking](https://github.com/AFNetworking/AFNetworking) v1.1.0

クライアント側（Android）

* Mac OS X Lion v10.7.4
* Nexus 7(ME370T)
* Android 4.2.2
* Eclipse JUNO v4.2.2
* [socket.io-java-client](https://github.com/Gottox/socket.io-java-client)


サーバ側構築の注意
------------------

1. 接続先のURLを定数定義（commons.js, Commons.m, Common.java）に設定してください。

クライアント側構築の注意
------------------------

1. 依存ライブラリのインストールのために、[CocoaPods](http://cocoapods.org)を利用しています。手順に従ってCocoaPodsをインストール・セットアップの後、`pod install`を行なってください。

License
-------

Copyright(C) 2013 TIS Inc. Nobuyuki Matsui (matsui.nobuyuki@tis.co.jp)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
