package com.company;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Lab2 implements GLEventListener{

    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        DDA(gl, 4,4,7,10);

    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }



    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }


    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {

        gl.glPointSize(3.0f);
        gl.glColor3d(1, 1, 1);

        //write your own code
        float m;
        float dx = x2-x1;
        float dy = y2-y1;
        m = dy/dx;

        if(m<1 || m==1){
            int xinit = (int)x1;
            int xfin = (int)x2;
            for(int i = xinit; i <= xfin; i++){
                double ynew = y1+m;
                //rounding off y
                int y_round = (int)Math.round(ynew);
                gl.glBegin(GL2.GL_POINTS);//static field
                gl.glVertex2d(xinit, y_round);
                gl.glEnd();
                xinit++;
                y1 = (float) ynew;
            }
        }
        else{
            int yinit = (int)y1;
            int yfin = (int)y2;
            for(int i = yinit; i <= yfin; i++){
                double xnew = x1+(1/m);
                //rounding off x
                int x_round = (int)Math.round(xnew);
                gl.glBegin(GL2.GL_POINTS);//static field
                gl.glVertex2d(x_round, yinit);
                gl.glEnd();
                yinit++;
                x1 = (float) xnew;
            }

        }

    }






    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab2 l = new Lab2();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;