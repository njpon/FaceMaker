/* Nathaniel Pon
   9/8/2020
 */

package com.example.facemaker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class Face extends SurfaceView {

    //initialize the variables required to create a face
    private int hairColor;
    private int skinColor;
    private int eyeColor;
    private int hairStyle;
    private boolean hairSelected;
    private boolean skinSelected;
    private boolean eyesSelected;
    private int r;
    private int g;
    private int b;
    private int black = Color.BLACK;
    private int white = Color.WHITE;
    Paint hairPaint = new Paint();
    Paint eyePaint = new Paint();
    Paint skinPaint = new Paint();
    Paint pupils = new Paint();
    Paint outerEye = new Paint();

    /**
     * The constructor for our Face class
     *
     * @Nathaniel Pon
     *
     * @param context
     * @param attrs
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(white);
        randomize();

        skinPaint.setColor(skinColor);
        hairPaint.setColor(hairColor);
        eyePaint.setColor(eyeColor);

        outerEye.setColor(white);
        pupils.setColor(black);
    }

    /**
     * randomize()
     * assigns the color variables a random color and assigns the hairStyle to any number from 0
     * to 2
     *
     * @Nathaniel Pon
     */
    public void randomize() {
        hairStyle = (int)(Math.random()*3);
        skinColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        hairColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        eyeColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }

    /**
     * Setter method for hairSelected
     * @Nathaniel Pon
     */
    public void setHairSelected() {
        hairSelected = true;
        skinSelected = false;
        eyesSelected = false;
    }
    /**
     * Setter method for skinSelected
     * @Nathaniel Pon
     */
    public void setSkinSelected() {
        skinSelected = true;
        eyesSelected = false;
        hairSelected = false;
    }
    /**
     * Setter method for eyesSelected
     * @Nathaniel Pon
     */
    public void setEyesSelected() {
        eyesSelected = true;
        skinSelected = false;
        hairSelected = false;
    }
    /**
     * Setter method for hairStyle
     * @Nathaniel Pon
     *
     * @param i
     */
    public void setHairStyle(int i) {
        this.hairStyle = i;
    }
    /**
     * Setter method for hairColor
     * @Nathaniel Pon
     *
     * @param r
     * @param g
     * @param b
     */
    public void setHairColor(int r, int g, int b) {
        hairColor = Color.rgb(r,g,b);
    }
    /**
     * Setter method for skinColor
     * @Nathaniel Pon
     *
     * @param r
     * @param g
     * @param b
     */
    public void setSkinColor(int r, int g, int b) {
        skinColor = Color.rgb(r,g,b);
    }
    /**
     * Setter method for eyeColor
     * @Nathaniel Pon
     *
     * @param r
     * @param g
     * @param b
     */
    public void setEyeColor(int r, int g, int b) {
        eyeColor = Color.rgb(r,g,b);
    }

    /**
     * Setter methods for hairSelected, skinSelected, eyeSelected,
     * hairColor, skinColor, eyeColor, hairStyle
     *
     * @Nathaniel Pon
     */
    public boolean getHairSelected() {return hairSelected;}
    public boolean getSkinSelected() {return skinSelected;}
    public boolean getEyeSelected() {return eyesSelected;}
    public int getHairColor() {return hairColor;}
    public int getSkinColor() {return skinColor;}
    public int getEyeColor() {return eyeColor;}
    public int getHairStyle() {return hairStyle;}

    /**
     * Draws the head of the face using a circle filled in with skinPaint
     *
     * @Nathaniel Pon
     *
     * @param canvas
     */
    public void drawHead(Canvas canvas) {
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 400, skinPaint);
    }

    /**
     * Draws two eyes using the pupils paint, outerEye paint, and eyePaint
     *
     * @Nathaniel Pon
     *
     * @param canvas
     */
    public void drawEyes(Canvas canvas) {
        canvas.drawCircle(canvas.getWidth()/2 + 170, canvas.getHeight()/2 - 100, 74, pupils);
        canvas.drawCircle(canvas.getWidth()/2 + 170, canvas.getHeight()/2 - 100, 70, outerEye);
        canvas.drawCircle(canvas.getWidth()/2 + 170, canvas.getHeight()/2 - 100, 40, eyePaint);
        canvas.drawCircle(canvas.getWidth()/2 + 170, canvas.getHeight()/2 - 100, 25, pupils);

        canvas.drawCircle(canvas.getWidth()/2 - 170, canvas.getHeight()/2 - 100, 74, pupils);
        canvas.drawCircle(canvas.getWidth()/2 - 170, canvas.getHeight()/2 - 100, 70, outerEye);
        canvas.drawCircle(canvas.getWidth()/2 - 170, canvas.getHeight()/2 - 100, 40, eyePaint);
        canvas.drawCircle(canvas.getWidth()/2 - 170, canvas.getHeight()/2 - 100, 25, pupils);
    }

    /**
     * Draws the hair based on spinner selection and uses hairPaint as its color
     *
     * @Nathaniel Pon
     *
     * @param canvas
     */
    public void drawHair(Canvas canvas) {
        if (hairStyle == 0) {
            Rect ugly = new Rect(canvas.getWidth()/2 - 420, canvas.getHeight()/2 - 800, canvas.getWidth()/2 + 420, canvas.getHeight()/2);
            canvas.drawRect(ugly, hairPaint);
        }
        else if (hairStyle == 1) {
            Path cool = new Path();
            cool.moveTo(canvas.getWidth()/2 - 400, canvas.getHeight()/2);
            cool.lineTo(canvas.getWidth()/2, canvas.getHeight()/2 - 700);
            cool.lineTo(canvas.getWidth()/2 + 400, canvas.getHeight()/2);
            cool.lineTo(canvas.getWidth()/2 - 400, canvas.getHeight()/2);
            canvas.drawPath(cool, hairPaint);
        }
        else if (hairStyle == 2) {

        }
    }

    /**
     * Draws the mouth using the drawArc method and pupils paint as a basic black paint
     *
     * @Nathaniel Pon
     *
     * @param canvas
     */
    //Suppressed because I don't think it matters if the drawMouth method requires a certain
    //OS to work. Otherwise, there would be an error
    @SuppressLint("NewApi")
    public void drawMouth(Canvas canvas) {
        canvas.drawArc(canvas.getWidth()/2 - 200, canvas.getHeight()/2 + 50, canvas.getWidth()/2 + 200, canvas.getHeight()/2 + 250, 180, -180, false, pupils);
    }

    /**
     * The onDraw method that synthesizes all of the drawing methods
     *
     * @Nathaniel Pon
     *
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        skinPaint.setColor(skinColor);
        hairPaint.setColor(hairColor);
        eyePaint.setColor(eyeColor);
        drawHair(canvas);
        drawHead(canvas);
        drawMouth(canvas);
        drawEyes(canvas);

    }
}
