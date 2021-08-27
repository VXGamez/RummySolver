import 'package:flutter/material.dart';

import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:google_fonts/google_fonts.dart';

class SplashScreen extends StatelessWidget {

  

  @override
  Widget build(BuildContext context) {
    var colorizeColors = [
      Colors.white,
      Colors.white,
      Colors.blue.shade800,
      Colors.blue,
    ];

    var s = MediaQuery.of(context).size;

    return Material(
      child: Container(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Image(
                image: AssetImage('assets/images/splash_image.png'),
                height: s.height*0.45,
              ),
              SizedBox(
                height: 20,
              ),
              AnimatedTextKit(
                animatedTexts: [
                    ColorizeAnimatedText(
                      'Rummy Solver',
                      textStyle:  GoogleFonts.poppins(
                        textStyle: TextStyle(
                          color: Colors.blue, 
                          letterSpacing: .3,
                          fontSize: 60
                        ),
                      ),
                      colors: colorizeColors,
                    ),
                  ],
                pause: Duration(milliseconds: 500),
                repeatForever: true,
                isRepeatingAnimation: true,
              ),
            ],
          ),
          decoration: BoxDecoration(
                image: DecorationImage(fit: BoxFit.cover,
                  image: AssetImage("assets/images/fons.png",),
                ),
          )
      ),
    );
  }
}