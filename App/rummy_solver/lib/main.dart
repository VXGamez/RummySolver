import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


import 'screens/screens.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {

      SystemChrome.setPreferredOrientations([
        DeviceOrientation.landscapeLeft,
        DeviceOrientation.landscapeRight
      ]);


    return MaterialApp(
      title: 'Rummy Solver',
      debugShowCheckedModeBanner: false,
      home: FutureBuilder(
        future: _loadContent(),
        builder: (BuildContext c, AsyncSnapshot<String> snapshot) {
          // if (snapshot.hasData) {
          //   return MainScreen();
          // }else if(snapshot.hasError){
          //   return ErrorScreen();
          // }else{
             return SplashScreen();
          // }
        },
      ),
    );
  }


  _loadContent(){
    return Future<String>.delayed(
      const Duration(seconds: 2),
      () => 'Data Loaded',
    );
  }

}