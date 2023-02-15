package lbParticles_v1_X;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.FileHandle;
import lbParticles_v1_X.ParticleEngine_v1.AnyField.AnyFieldTargettedOne;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleInstance.BasicParticleInstance;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleInstance.MoveableInstance;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleType.BasicParticleType;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleType.BeamingParticleType;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.AnyVariableListener;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.AnyVariableListener.MultyVariableListener;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.AnyVariableListener.VariableRemovalMessage;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.NumberVariable;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.PointVariable;
import lbParticles_v1_X.ParticleEngine_v1.Engine.point2f;
import lbParticles_v1_X.ParticleEngine_v1.Engine.point2i;
import lbParticles_v1_X.ParticleEngine_v1.UserInterface.lbKeyBoardHandle;
import lbParticles_v1_X.ParticleEngine_v1.UserInterface.lbMouseHandle;
import lbParticles_v1_X.ParticleEngine_v1.lbGUIelement.lbButton;
import lbParticles_v1_X.ParticleEngine_v1.lbGUIelement.lbGUIlistener;
import lbParticles_v1_X.ParticleEngine_v1.lbGUIelement.lbGUIlistener.lbGUIaction;


public class ParticleEngine_v1 extends JPanel implements KeyListener {
	public static final long serialVersionUID = -4209373792482117685L;

	public static void log(String message) { System.out.println(message); }
	
	public static float handleFinity(float f, float min, float max) {
		if(f == 0.f || f == -0.f) { return f; } if(Float.isFinite(f)) { return f; }
		return (float) (Math.random() * (max - min) + min);
	}

	public static class UserInterface {
		public static class lbKeyBoardHandle {
			public boolean a=false,b=false,c=false,d=false,e=false,f=false,g=false,h=false,i=false,
					j=false,k=false,l=false,m=false,n=false,o=false,p=false,r=false,s=false,t=false,
					u=false,v=false,y=false,z=false,q=false,w=false;
			public boolean up=false,down=false,left=false,right=false,control=false,alt=false, delete=false,
					altgraph=false,shift=false,enter=false,capslock=false,tab=false,comma=false,period=false,escape=false;
			public boolean quote=false,_0=false,_1=false,_2=false,_3=false,_4=false,_5=false,_6=false,_7=false,_8=false,_9=false;
			public boolean insert=false,np1=false,np2=false,np3=false,np4=false,np5=false,np6=false,np7=false,np8=false,np9=false;
			public boolean starN=false,minusN=false,backspace=false,numlock=false,nslash=false,star=false,substract=false;
			public boolean ALT() { return alt || altgraph; } public boolean Star() { return star || starN; }
			public void onKeyPress(int keyCode) { switch(keyCode) {
			case KeyEvent.VK_COMMA:comma=true;break;case KeyEvent.VK_PERIOD:period=true;break;
			case KeyEvent.VK_DELETE:delete=true;break;case KeyEvent.VK_BACK_SPACE:backspace=true;break;
			case KeyEvent.VK_MINUS:minusN=true;break;case KeyEvent.VK_SUBTRACT:substract=true;break;
			case KeyEvent.VK_ESCAPE:escape=true;break;case KeyEvent.VK_NUM_LOCK:numlock=true;break;
			case KeyEvent.VK_INSERT:insert=true;break;case KeyEvent.VK_NUMPAD1:np1=true;break;
			case KeyEvent.VK_NUMPAD2:np2=true;break;case KeyEvent.VK_NUMPAD3:np3=true;break;
			case KeyEvent.VK_NUMPAD4:np4=true;break;case KeyEvent.VK_NUMPAD5:np5=true;break;
			case KeyEvent.VK_NUMPAD6:np6=true;break;case KeyEvent.VK_NUMPAD7:np7=true;break;
			case KeyEvent.VK_NUMPAD8:np8=true;break;case KeyEvent.VK_NUMPAD9:np9=true;break;
			case KeyEvent.VK_A:a=true;break;case KeyEvent.VK_B:b=true;break;
			case KeyEvent.VK_C:c=true;break;case KeyEvent.VK_D:d=true;break;
			case KeyEvent.VK_E:e=true;break;case KeyEvent.VK_F:f=true;break;
			case KeyEvent.VK_G:g=true;break;case KeyEvent.VK_H:h=true;break;
			case KeyEvent.VK_I:i=true;break;case KeyEvent.VK_J:j=true;break;
			case KeyEvent.VK_K:k=true;break;case KeyEvent.VK_L:l=true;break;
			case KeyEvent.VK_M:m=true;break;case KeyEvent.VK_N:n=true;break;
			case KeyEvent.VK_O:o=true;break;case KeyEvent.VK_P:p=true;break;
			case KeyEvent.VK_R:r=true;break;case KeyEvent.VK_S:s=true;break;
			case KeyEvent.VK_T:t=true;break;case KeyEvent.VK_U:u=true;break;
			case KeyEvent.VK_V:v=true;break;case KeyEvent.VK_Y:y=true;break;
			case KeyEvent.VK_Z:z=true;break;case KeyEvent.VK_Q:q=true;break;
			case KeyEvent.VK_W:w=true;break;case KeyEvent.VK_UP:up=true;break;
			case KeyEvent.VK_DOWN:down=true;break;case KeyEvent.VK_LEFT:left=true;break;
			case KeyEvent.VK_RIGHT:right=true;break;case KeyEvent.VK_CONTROL:control=true;break;
			case KeyEvent.VK_ALT:alt=true;break;case KeyEvent.VK_ALT_GRAPH:altgraph=true;break;
			case KeyEvent.VK_SHIFT:shift=true;break;case KeyEvent.VK_ENTER:enter=true;break;
			case KeyEvent.VK_CAPS_LOCK:capslock=true;break;case KeyEvent.VK_TAB:tab=true;break;
			case KeyEvent.VK_QUOTEDBL:quote=true;break;
			case KeyEvent.VK_1:_1=true;break;case KeyEvent.VK_2:_2=true;break;
			case KeyEvent.VK_3:_3=true;break;case KeyEvent.VK_4:_4=true;break;
			case KeyEvent.VK_5:_5=true;break;case KeyEvent.VK_6:_6=true;break;
			case KeyEvent.VK_7:_7=true;break;case KeyEvent.VK_8:_8=true;break;
			case KeyEvent.VK_9:_9=true;break;case KeyEvent.VK_0:_0=true;break;
			} }
			public void onKeyRelease(int keyCode) { switch(keyCode) {
			case KeyEvent.VK_COMMA:comma=false;break;case KeyEvent.VK_PERIOD:period=false;break;
			case KeyEvent.VK_DELETE:delete=false;break;case KeyEvent.VK_BACK_SPACE:backspace=false;break;
			case KeyEvent.VK_MINUS:minusN=false;break;case KeyEvent.VK_SUBTRACT:substract=false;break;
			case KeyEvent.VK_ESCAPE:escape=false;break;case KeyEvent.VK_NUM_LOCK:numlock=false;break;
			case KeyEvent.VK_INSERT:insert=false;break;case KeyEvent.VK_NUMPAD1:np1=false;break;
			case KeyEvent.VK_NUMPAD2:np2=false;break;case KeyEvent.VK_NUMPAD3:np3=false;break;
			case KeyEvent.VK_NUMPAD4:np4=false;break;case KeyEvent.VK_NUMPAD5:np5=false;break;
			case KeyEvent.VK_NUMPAD6:np6=false;break;case KeyEvent.VK_NUMPAD7:np7=false;break;
			case KeyEvent.VK_NUMPAD8:np8=false;break;case KeyEvent.VK_NUMPAD9:np9=false;break;
			case KeyEvent.VK_A:a=false;break;case KeyEvent.VK_B:b=false;break;
			case KeyEvent.VK_C:c=false;break;case KeyEvent.VK_D:d=false;break;
			case KeyEvent.VK_E:e=false;break;case KeyEvent.VK_F:f=false;break;
			case KeyEvent.VK_G:g=false;break;case KeyEvent.VK_H:h=false;break;
			case KeyEvent.VK_I:i=false;break;case KeyEvent.VK_J:j=false;break;
			case KeyEvent.VK_K:k=false;break;case KeyEvent.VK_L:l=false;break;
			case KeyEvent.VK_M:m=false;break;case KeyEvent.VK_N:n=false;break;
			case KeyEvent.VK_O:o=false;break;case KeyEvent.VK_P:p=false;break;
			case KeyEvent.VK_R:r=false;break;case KeyEvent.VK_S:s=false;break;
			case KeyEvent.VK_T:t=false;break;case KeyEvent.VK_U:u=false;break;
			case KeyEvent.VK_V:v=false;break;case KeyEvent.VK_Y:y=false;break;
			case KeyEvent.VK_Z:z=false;break;case KeyEvent.VK_Q:q=false;break;
			case KeyEvent.VK_W:w=false;break;case KeyEvent.VK_UP:up=false;break;
			case KeyEvent.VK_DOWN:down=false;break;case KeyEvent.VK_LEFT:left=false;break;
			case KeyEvent.VK_RIGHT:right=false;break;case KeyEvent.VK_CONTROL:control=false;break;
			case KeyEvent.VK_ALT:alt=false;break;case KeyEvent.VK_ALT_GRAPH:altgraph=false;break;
			case KeyEvent.VK_SHIFT:shift=false;break;case KeyEvent.VK_ENTER:enter=false;break;
			case KeyEvent.VK_CAPS_LOCK:capslock=false;break;case KeyEvent.VK_TAB:tab=false;break;
			case KeyEvent.VK_QUOTEDBL:quote=false;break;
			case KeyEvent.VK_1:_1=false;break;case KeyEvent.VK_2:_2=false;break;
			case KeyEvent.VK_3:_3=false;break;case KeyEvent.VK_4:_4=false;break;
			case KeyEvent.VK_5:_5=false;break;case KeyEvent.VK_6:_6=false;break;
			case KeyEvent.VK_7:_7=false;break;case KeyEvent.VK_8:_8=false;break;
			case KeyEvent.VK_9:_9=false;break;case KeyEvent.VK_0:_0=false;break;
			} }
		}

		/******************************************************************************************
		 * Simple Mouse Handling class for that for knowing lot's of things about mouse.
		 * For using this object you can eighter add this as a "MouseListener" to a "JPanel"
		 * (not to a "Jframe") or you can call "onPress", "onRelease" on related functions
		 * but for handling "mouseInside" you need to set it on your own,
		 * but hopefully it is not changing any of other methods working style.
		 * Boolean "left", "right", "middle" indicates if they are down.
		 * Booleans that ends with "Readed" are setted to false only when
		 * the related key released automatically and there is not
		 * any other action, so YOU can handle button presses.
		 * The "mouseWheel" starts at 0 and as wheel events happen it changes,
		 * so you can learn scroll of wheel relative to the one at start of aplication.
		 * Integers that end with "for" meant to tell you how many updates they stayed
		 * in the same state, for using this utility you must call lbUpdate() in every update.
		 ******************************************************************************************/
		public static class lbMouseHandle implements MouseListener, MouseWheelListener {
			public boolean left=false, right=false, midle=false, mouseInside=false;
			public boolean leftReaded=false, rightReaded=false, middleReaded=false;
			public point2f leftLastPressPosition=null, leftLastReleasePosition=null;
			public point2f rightLastPressPosition=null, rightLastReleasePosition=null;
			public int leftfor = 0, rightfor = 0, wheel = 0;
			public void onPress(MouseEvent e) {
				switch(e.getButton()) {
				case MouseEvent.BUTTON2: midle = true; break;
				case MouseEvent.BUTTON1:
					left = true; leftfor = 0;
					leftLastPressPosition = new point2f(e.getX(), e.getY());
					break;
				case MouseEvent.BUTTON3:
					right = true; rightfor = 0;
					rightLastReleasePosition = new point2f(e.getX(), e.getY());
					break;
				}
			}
			public void onRelease(MouseEvent e) {
				switch(e.getButton()) {
				case MouseEvent.BUTTON2: midle = false; middleReaded = false; break;
				case MouseEvent.BUTTON1:
					left = false; leftfor = 0; leftReaded = false;
					leftLastReleasePosition = new point2f(e.getX(), e.getY());
					break;
				case MouseEvent.BUTTON3:
					right = false; rightfor = 0; rightReaded = false;
					rightLastReleasePosition = new point2f(e.getX(), e.getY());
					break;
				}
			}
			public void lbUpdate() { leftfor++; rightfor++; }
			public point2f getPosition() {
				Point p = MouseInfo.getPointerInfo().getLocation();
				return new point2f(p.x, p.y);
			}
			public point2f getPosition(Component relative) {
				Point p = relative.getLocationOnScreen(); point2f r = getPosition();
				r.x -= p.x; r.y -= p.y;
				return r;
			}
			@Override public void mousePressed(MouseEvent e) { onPress(e); }
			@Override public void mouseReleased(MouseEvent e) { onRelease(e); }
			@Override public void mouseEntered(MouseEvent e) { mouseInside = true; }
			@Override public void mouseExited(MouseEvent e) { mouseInside = false; }
			@Override public void mouseClicked(MouseEvent e) { }
			@Override public void mouseWheelMoved(MouseWheelEvent e) { wheel += e.getWheelRotation(); }
		}
	}
	public static class Engine {

		/** Simple class for representing a point in a 2 dimensional space with floats.
		 *  "Amplitude" means "Math.sqrt(x*x+y*y)",
		 *  "minus" means "-x, -y", "oneover" means "1.f/x, 1.f/y",
		 *  "mirrorto" gets symmetry, "mirrored" generetes a new point which is symmetric,
		 *  "increase" increases current point, "sumwith" make a new point which is the sum,
		 * 	"amplifie" multiplies the current point, "multiplie" returns a new point which is multiplication.
		 */
		static public class point2f {
			public float x, y; public point2f() { x=0.f;y=0.f; }
			point2f(float x, float y) { this.x=x;this.y=y; } point2f(point2f o) { x=o.x;y=o.y; }
			public point2f(float o) { x=o;y=o; } public point2f(point2i o) { x=o.x;y=o.y; }
			public float getamplitude() { return (float) Math.sqrt(x*x+y*y); }
			public point2f setamplitude(float amplitude) { amplifie(amplitude/getamplitude()); return this; }
			public point2f disamplitude() { float a = getamplitude(); x/=a;y/=a; return this; }
			public point2f minus() { return new point2f(-x,-y); }
			public point2f oneover() { return new point2f(1/x,1/y); }
			public point2i toInt() { return new point2i(this); }
			public point2f absolute() { x=x<0.f ? -x:x; y=y<0.f ? -y:y; return this; }
			@Override public String toString() { return x+"|"+y; }
			@Override public boolean equals(Object other) { return x==((point2f)other).x && y==((point2f)other).y; }
			@Override protected Object clone() throws CloneNotSupportedException { return new point2f(this); }
			
			public point2f mirrorto(point2f o) { x=o.x*2-x;y=o.y*2-y; return this; }
			public point2f mirrorto(float ox, float oy) { x=ox*2-x;y=oy*2-y; return this; }
			public point2f mirroredto(point2f o) { return new point2f(x=o.x*2-x,y=o.y*2-y); }
			public point2f mirroredto(float ox, float oy) { return new point2f(x=ox*2-x,y=oy*2-y); }
			public float distanceto(point2f o) { float dx=x-o.x, dy=y-o.y; return (float) Math.sqrt(dx*dx+dy*dy); }
			public float distanceto(float ox, float oy) { float dx=x-ox, dy=y-oy; return (float) Math.sqrt(dx*dx+dy*dy); }
			
			public point2f copy(point2f o) { x=o.x;y=o.y; return this; }
			public point2f copy(float ox, float oy) { x=ox;y=oy; return this; }
			public point2f copy(float o) { x=o;y=o; return this; }
			
			public point2f increase(point2f o) { x+=o.x;y+=o.y; return this; }
			public point2f sumwith(point2f o) { return new point2f(x+o.x,y+o.y); }
			public point2f amplifie(point2f o) { x*=o.x;y*=o.y; return this; }
			public point2f multiplie(point2f o) { return new point2f(x*o.x,y*o.y); }
			public point2f increase(float ox, float oy) { x+=ox;y+=oy; return this; }
			public point2f sumwith(float ox, float oy) { return new point2f(x+ox,y+oy); }
			public point2f amplifie(float ox, float oy) { x*=ox;y*=oy; return this; }
			public point2f multiplie(float ox, float oy) { return new point2f(x*ox,y*oy); }
			public point2f increase(float o) { x+=o;y+=o; return this; }
			public point2f sumwith(float o) { return new point2f(x+o,y+o); }
			public point2f amplifie(float o) { x*=o;y*=o; return this; }
			public point2f multiplie(float o) { return new point2f(x*o,y*o); }
		}
		/** Has no other purpose than stroing a point with integers. Designed for using point2f in drawing with ease.*/
		public static class point2i { 
			public point2i(point2f o) { x=(int)o.x;y=(int)o.y; }
			public point2i(int x, int y) { this.x = x; this.y = y; }
			public point2f toFloat() { return new point2f(x, y); }
			
			@Override public String toString() { return x+"|"+y; }
			@Override public boolean equals(Object other) { return x==((point2f)other).x && y==((point2f)other).y; }
			@Override protected Object clone() throws CloneNotSupportedException { return new point2f(this); }
			
			public int x,y;

		}
		public static interface GameWorld {
			/** Height is the display's height*/ public int lbWidth();
			/** Width is the display's width*/ public int lbHeight();
			/** Zoom is the ratio displayPixels per WorldPoints. */ public float getXzoomRatio();
			/** Zoom is the ratio displayPixels per WorldPoints. */ public float getYzoomRatio();
			/** Just the center of the display */public point2f getCenter();
			/** Map a point in display to world. */
			public default point2f mapD2W(point2f p) {
				point2f result = new point2f(p.x, lbHeight() - p.y);
				result.increase(lbWidth()*-0.5f, lbHeight()*-0.5f);
				result.amplifie(1.f/getXzoomRatio(), 1.f/getYzoomRatio());
				result.increase(getCenter());
				return result;
			}
			/** Map a point in world to display. */
			public default point2f mapW2D(point2f p) {
				point2f result = getCenter().minus().increase(p);
				result.amplifie(getXzoomRatio(), getYzoomRatio());
				result.increase(lbWidth()*+0.5f, lbHeight()*+0.5f);
				result.y = lbHeight() - result.y;
				return result;
			}
		}
		
	}
	public static class lbMutex {
		boolean isLocked = false;
		String name = null;
		public String getStatus() {
			if(isLocked) { return "locked"; } else { return "not locked"; }
		}
		public lbMutex(String name) { this.name = name; }
		public void lock() {
			while(isLocked) { try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); } }
			isLocked = true;
		}
		public void unlock() { isLocked = false; }
		public void dlock(String requester) {
			log(name+" was "+getStatus()+" and "+requester+" requested to lock "+name);
			for(int i=0;isLocked;i++) {
				log("iteration:"+i);
				try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
			} isLocked = true;
			log(requester+" succesfully locked "+name);
		}
		public void dunlock(String requester) {
			log(name+" was "+getStatus()+" and unlocked by:"+requester);
			isLocked = false;
		}
	}

	public float zoomX = 1.f, zoomY = 1.f; public point2f center = new point2f();
	
	public float getXzoomRatio() { return zoomX; }
	public float getYzoomRatio() { return zoomY; }
	public point2f getCenter() { return center; }
	/** Map a point in display to world. */
	public point2f mapD2W(point2f p) {
		point2f result = new point2f(p.x, lbHeight() - p.y);
		result.increase(lbWidth()*-0.5f, lbHeight()*-0.5f);
		result.amplifie(1.f/getXzoomRatio(), 1.f/getYzoomRatio());
		result.increase(getCenter());
		return result;
	}
	/** Map a point in world to display. */
	public point2f mapW2D(point2f p) {
		point2f result = getCenter().minus().increase(p);
		result.amplifie(getXzoomRatio(), getYzoomRatio());
		result.increase(lbWidth()*+0.5f, lbHeight()*+0.5f);
		result.y = lbHeight() - result.y;
		return result;
	}
	
	public static abstract class ParticleEngineGUI implements lbGUIlistener {
		public point2f center, size, centerBeforeGrab;
		public static ParticleEngine_v1 owner;
		public String name = "unnamed GUI";
		public lbButton delete, rename, move;
		public boolean isMouseGrabbed = false, isCenterGrabbed = false;
		
		public int handleHower() {
			int result = 0; point2f distance = center.minus().increase(owner.mposWorld).absolute();
			if(distance.x<size.x/2.f && distance.y<size.y/2.f
			&& !owner.owner.isMIopen && !owner.Mhandle.leftReaded)
				{ result += 1; }
			distance = owner.mapD2W(new point2f(owner.lbWidth()/2.f, owner.lbHeight()/2.f)).minus().sumwith(center).absolute();
			if(distance.x<size.x/2.f && distance.y<size.y/2.f
			&& !owner.owner.isMIopen && !owner.entered)
				{ result += 2; }
			return result;
		}
		public void handleGrab() {
			if(isMouseGrabbed) {
				point2f change = owner.mapD2W(owner.Mhandle.leftLastPressPosition).minus().increase(owner.mposWorld);
				if(owner.kbhandle.w) { centerBeforeGrab.y += owner.cspeed; }
				if(owner.kbhandle.s) { centerBeforeGrab.y -= owner.cspeed; }
				if(owner.kbhandle.d) { centerBeforeGrab.x += owner.cspeed; }
				if(owner.kbhandle.a) { centerBeforeGrab.x -= owner.cspeed; }
				center = centerBeforeGrab.sumwith(change);
				if(!owner.Mhandle.left) { isMouseGrabbed = false; }
				return;
			}
			if(isCenterGrabbed) {
				if(owner.kbhandle.a) { center.x -= owner.cspeed; }
				if(owner.kbhandle.d) { center.x += owner.cspeed; }
				if(owner.kbhandle.w) { center.y += owner.cspeed; }
				if(owner.kbhandle.s) { center.y -= owner.cspeed; }
				
				if(!owner.kbhandle.enter) { isCenterGrabbed = false; return; }
				return;
			}
			int hower = handleHower(); if(hower == 0) { return; }
			if(hower%2 == 1 && owner.Mhandle.left) {
				centerBeforeGrab = new point2f(center);
				isMouseGrabbed = true; owner.Mhandle.leftReaded = true;
				if(owner.toFront == null) { owner.toFront = this; }
				return;
			}
			if(hower%4 >= 2 && owner.kbhandle.enter) {
				isCenterGrabbed = true; owner.entered = true;
				if(owner.toFront == null) { owner.toFront = this; }
				return;
			}
		}
		
		public ParticleEngineGUI() { this.center =  new point2f(owner.center); }
		
		public void handleMove() {
			if(owner.kbhandle.left) { center.x -= owner.cspeed; }
			if(owner.kbhandle.right) { center.x += owner.cspeed; }
			if(owner.kbhandle.up) { center.y += owner.cspeed; }
			if(owner.kbhandle.down) { center.y -= owner.cspeed; }
		}
		
		public abstract void lbUpdate(); public abstract void lbPaint(Graphics2D target);
		public abstract void deleteSelf(); public abstract char[] getTypeName();
		
	}
	
	/********************************************************************************************************
	 * The center variable supposed to be relative to owner.
	 * Has the Listener Interface, Event Class,
	 * Has types : Button
	 ********************************************************************************************************/
	public static abstract class lbGUIelement {
		public static interface lbGUIlistener {
			public void onlbGUIaction(lbGUIaction action);
			
			public static class lbGUIaction {
				public lbGUIaction(lbGUIelement reason) {
					this.reason = reason;
				}
				public lbGUIelement reason;
			}
		}
		
		public static Color text = new Color(191, 255, 191), titleText = new Color(255, 95, 255),
							element = new Color(63, 95, 63), elementHover = new Color(95, 63, 95),
							on = new Color(15, 255, 15), off = new Color(15, 0, 15);
		
		public static final point2f tetralsize = new point2f(40, 20), pentalsize = new point2f(50, 20),
								hekzalsize = new point2f(60, 20), hentalsize = new point2f(70, 20),
								octalsize = new point2f(80, 20), nonansize = new point2f(90, 20),
								decansize = new point2f(100, 20), decamonansize = new point2f(110, 20),
								decadiensize = new point2f(120, 20), decatriensize = new point2f(130, 20),
								decaTetransize = new point2f(110, 20),
								decahekzansize = new point2f(160, 20);
		
		public static final point2f rtetralsize = new point2f(40, 20), rpentalsize = new point2f(50, 20),
								rhekzalsize = new point2f(60, 20), rhentalsize = new point2f(70, 20),
								roctalsize = new point2f(80, 20), rnonansize = new point2f(90, 20),
								rdecansize = new point2f(100, 20), rdecamonansize = new point2f(110, 20),
								rdecadiensize = new point2f(120, 20), rdecatriensize = new point2f(130, 20),
								rdecaTetransize = new point2f(110, 20),
								rdecahekzansize = new point2f(160, 20);
		
		public static void handleZoom(float zoom) {
			rtetralsize.copy(tetralsize.multiplie(zoom));
			rpentalsize.copy(pentalsize.multiplie(zoom));
			rhekzalsize.copy(hekzalsize.multiplie(zoom));
			rhentalsize.copy(hentalsize.multiplie(zoom));
			roctalsize.copy(octalsize.multiplie(zoom));
			rnonansize.copy(nonansize.multiplie(zoom));
			rdecansize.copy(decansize.multiplie(zoom));
			rdecamonansize.copy(decamonansize.multiplie(zoom));
			rdecadiensize.copy(decadiensize.multiplie(zoom));
			rdecatriensize.copy(decatriensize.multiplie(zoom));
			rdecaTetransize.copy(decaTetransize.multiplie(zoom));
			rdecahekzansize.copy(decahekzansize.multiplie(zoom));
		}
		
		public lbGUIlistener listener;
		public boolean ishovering = false, accepting = false;
		public point2f center, size;
		
		public lbGUIelement(lbGUIlistener listener) { this.listener = listener; }
		 
		public int handleHower(ParticleEngine_v1 engine, point2f start) {
			point2f spos = engine.mapW2D(center.sumwith(start)).minus();
			point2f dpos = spos.sumwith(engine.mousepos).absolute();
			int res = 0;
			if(dpos.x<size.x/2.f && dpos.y<size.y/2.f &&
				!engine.owner.isMIopen && !engine.Mhandle.leftReaded)
				{ res += 1; }
			dpos = spos.sumwith(engine.lbWidth()/2.f, engine.lbHeight()/2.f).absolute();
			if(dpos.x<size.x/2.f && dpos.y<size.y/2.f &&
				!engine.owner.isMIopen && !engine.entered)
				{ res += 2; }
			ishovering = res != 0; return res;
		}
		
		public abstract void lbUpdate(ParticleEngine_v1 engine, point2f start);
		public abstract void lbPaint(Graphics2D target, ParticleEngine_v1 owner, point2f start);
		
		public static class lbButton extends lbGUIelement {
			public lbButton(lbGUIlistener listener, point2f center, point2f size, String name) {
				super(listener); this.name = name; this.center = center; this.size = size;
			}
			String name;
			
			public void acceptClick() { }

			@Override
			public void lbUpdate(ParticleEngine_v1 engine, point2f start) {
				int hower = handleHower(engine, start); if(hower == 0) { accepting = false; return; }
				if(!engine.Mhandle.left && !engine.kbhandle.enter) { accepting = true; return; }
				if(!accepting) { return; }
				if(hower % 2 == 1 && engine.Mhandle.left) {
					lbGUIaction event = new lbGUIaction(this);
					accepting = false; engine.Mhandle.leftReaded = true;
					listener.onlbGUIaction(event);
				}
				if(hower >= 2 && engine.kbhandle.enter) {
					lbGUIaction event = new lbGUIaction(this);
					accepting = false; engine.entered = true;
					listener.onlbGUIaction(event);
				}
			}
			@Override
			public void lbPaint(Graphics2D target, ParticleEngine_v1 owner, point2f start) {
				if(ishovering) { target.setColor(elementHover); } else { target.setColor(element); }
				point2f rpos = owner.mapW2D(center.sumwith(start)).increase(size.multiplie(-0.5f));
				target.fillRect((int)rpos.x, (int)rpos.y, (int)size.x, (int)size.y);
				target.setColor(text); char[] chars = name.toCharArray();
				target.drawChars(chars, 0, chars.length, (int)(rpos.x +10*owner.zoomed), (int)(rpos.y +size.y*7.f/10.f));
			}
			
			public static class lbButtonToggle extends lbButton {
				public lbButtonToggle(lbGUIlistener listener, point2f center, point2f size, String name) {
					super(listener, center, size, name);
				}
				public boolean istrue = false;
				
				public void acceptClick() { istrue = !istrue; }
				
				@Override
				public void lbPaint(Graphics2D target, ParticleEngine_v1 owner, point2f start) {
					if(ishovering) { target.setColor(elementHover); } else { target.setColor(element); }
					point2i rpos = owner.mapW2D(center.sumwith(start)).increase(size.multiplie(-0.5f)).toInt();
					target.fillRect(rpos.x, rpos.y, (int)size.x, (int)size.y);
					if(istrue) {
						target.setColor(on);
						target.fillRect((int)(rpos.x+size.x-6), rpos.y+4, 4, (int)(size.y-8));
					} else {
						target.setColor(off);
						target.fillRect((int)(rpos.x+size.x-6), (int)(rpos.y+size.y*2.f/5.f), 4, (int)(size.y/5.f));
					}
					target.setColor(text); char[] chars = name.toCharArray();
					target.drawChars(chars, 0, chars.length, rpos.x +(int)(10*owner.zoomed), rpos.y +(int)(size.y*7.f/10.f));
				}
			}
		}
		
	}
	
	public static abstract class AnyVariable extends ParticleEngineGUI {
		public AnyVariable() { super(); }
		public static interface AnyVariableListener {
			public static class MultyVariableListener implements AnyVariableListener {
				AnyVariable variable = null;
				ArrayList<AnyVariableListener> listeners = new ArrayList<AnyVariableListener>();
				@Override
				public void onVariableUpdate(AnyVariable update) {
					for(AnyVariableListener cowner : listeners) { cowner.onVariableUpdate(update); }
				}
				public void create(AnyVariable variable, AnyVariableListener nowner) {
					listeners.add(variable.listener); listeners.add(nowner);
					variable.listener = this; this.variable = variable;
				}
				@Override
				public void removeVariable(VariableRemovalMessage removal) {
					for(AnyVariableListener cowner : listeners) { cowner.removeVariable(removal); }
					if(this.variable == removal.variable) { this.variable = null; }
				}
				
			}
			public void onVariableUpdate(AnyVariable update);
			public void removeVariable(VariableRemovalMessage removal);
			
			public static class VariableRemovalMessage {
				AnyVariableListener listener; AnyVariable variable; /** -1 deletion, 0 not Madatory */ int index=0;
				public VariableRemovalMessage(AnyVariableListener listener, AnyVariable variable, int index)
				{ this.listener = listener; this.variable = variable; this.index = index; }
			}
		}
		
		public AnyVariableListener listener = null;
		public lbButton pick;
		
		public void removeListener(VariableRemovalMessage removal) {
			if(listener == removal.listener) { listener.removeVariable(removal); listener = null; return; }
			ArrayList<AnyVariableListener> listeners = ((MultyVariableListener) listener).listeners;
			for(int i=0;i!=listeners.size();i++) { if(listeners.get(i) == removal.listener) {
				removal.listener.removeVariable(removal); listeners.remove(i); return;
			} }
		}
		public void addListener(AnyVariableListener nlistener) {
			if(listener == null) { listener = nlistener; return; }
			if(listener.getClass() == MultyVariableListener.class) {
				((MultyVariableListener)listener).listeners.add(nlistener);
			} else { new MultyVariableListener().create(this, nlistener);; }
		}
		public void deleteSelf() {
			if(listener != null) {
				listener.removeVariable(new AnyVariableListener.VariableRemovalMessage(listener, this, -1));
				listener = null;
			}
			owner.removedGUI.add(this);
		}
		
		/**************************************************************************************************************
		 * A GUI for only one float variable.
		 * Can be moved, renamed, deleted, pickd.
		 * Variable can be changed or set by user.
		 * Doesn't assign it's position if not given in constractor.
		 **************************************************************************************************************/
		public static class NumberVariable extends AnyVariable {

			// 6 rows, spaced 10 pixels
			public static point2f rsize = new point2f();
			public static final char[] TYPENAME = "Variable: NUMBER".toCharArray();

			public static final point2f typeStart    = new point2f(5               ,   8  + 10*6 + 20*5);
			public static final point2f nameStart    = new point2f(10              ,   8  + 10*5 + 20*4);
			public static final point2f renameCenter = new point2f(60/2 + 5        , 20/2 + 10*4 + 20*3);
			public static final point2f moveCenter   = new point2f(50/2 + 5*2 + 60 , 20/2 + 10*4 + 20*3);
			public static final point2f deleteCenter = new point2f(60/2 + 5        , 20/2 + 10*3 + 20*2);
			public static final point2f pickCenter   = new point2f(40/2 + 5*4 + 60 , 20/2 + 10*3 + 20*2);
			public static final point2f valueStart   = new point2f(10              ,   8  + 10*2 + 20*1);
			public static final point2f cCenter      = new point2f(60/2 + 5        , 20/2 + 10);
			public static final point2f sCenter      = new point2f(40/2 + 5*4 + 60 , 20/2 + 10);
			
			public lbButton change, set;
			public boolean isc = false, iss = false,
							islistening = false,
							ismove = false, isrename = false;
			public float variable = 0.f;

			@Override public char[] getTypeName() { return TYPENAME; }
			public NumberVariable() { super();
				name = "unnamed number"; size = new point2f(5*3 + 110, 10*7 + 20*6);
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				change = new lbButton(this, cCenter, lbGUIelement.rhekzalsize, "change");
				set = new lbButton(this, sCenter, lbGUIelement.rtetralsize, "set");
			}

			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					change.lbUpdate(owner, start); set.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				change.handleHower(owner, start); set.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					isc = false; iss = false;
					islistening = false;
					ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(isc) { isc = false; islistening = false; owner.islistening = false; return; }
					if(iss) {
						try {
							variable = Float.valueOf(new String(owner.command)).floatValue();
						} catch(Exception e) { owner.command = new char[0]; return; }
						if(listener != null)  { listener.onVariableUpdate(this); }
						owner.command = new char[0]; owner.isCommandListening = false;
						islistening = false; iss = false;
						return;
					}
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(iss || isrename) { return; }
				
				if(isc) {
					if(owner.kbhandle.left ) { variable -= owner.cspeed; }
					if(owner.kbhandle.right) { variable += owner.cspeed; }
					if(listener != null)  { listener.onVariableUpdate(this); }
					return;
				}
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC);
				rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				change.lbPaint(target, owner, wstart); set.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				char[] nchars = name.toCharArray(), vchars = ("value:"+variable).toCharArray();
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i vpos = owner.mapW2D(wstart.sumwith(valueStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				target.drawChars(nchars, 0, nchars.length, npos.x, npos.y);
				target.drawChars(vchars, 0, vchars.length, vpos.x, vpos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == pick) { owner.chosenVariable = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				islistening = true;
				if(action.reason == change) { owner.islistening = true; isc = true; return; }
				if(action.reason == set) { owner.isCommandListening = true; iss = true; return; }
				if(action.reason == rename) { owner.isCommandListening = true; isrename = true; return; }
				if(action.reason == move) { owner.islistening = true; ismove = true; return; }
			}

		}
		
		/**************************************************************************************************************
		 * A GUI for only one point2f variable.
		 * Can be moved, renamed, deleted, pickd or can get position.
		 * Variable can be changed or set by user.
		 * Doesn't assign it's position if not given in constractor.
		 **************************************************************************************************************/
		public static class PointVariable extends AnyVariable implements AnyVariableListener {
			// 8 rows, spaced 10 pixels
			public static final point2f rsize = new point2f(10*4 + 110, 10*10 + 20*9);
			public static final char[] TYPENAME = "Variable: POINT".toCharArray();

			public static final point2f typeStart    = new point2f(10               , 8 + 10*9 + 20*8);
			public static final point2f nameStart    = new point2f(20               , 8 + 10*8 + 20*7);
			public static final point2f renameCenter = new point2f(60/2 + 10        , 20/2 + 10*7 + 20*6);
			public static final point2f moveCenter   = new point2f(50/2 + 10*2 + 70 , 20/2 + 10*7 + 20*6);
			public static final point2f deleteCenter = new point2f(60/2 + 10        , 20/2 + 10*6 + 20*5);
			public static final point2f showCenter   = new point2f(50/2 + 10*2 + 70 , 20/2 + 10*6 + 20*5);
			public static final point2f valueStart   = new point2f(10               , 8 + 10*5 + 20*4);
			
			public static final point2f pxCenter   = new point2f(40/2 + 10        , 20/2 + 10*4 + 20*3);
			public static final point2f pickCenter = new point2f(40/2 + 10+5 + 40 , 20/2 + 10*4 + 20*3);
			public static final point2f pyCenter   = new point2f(40/2 + 10+10 + 80, 20/2 + 10*4 + 20*3);
			public static final point2f getCenter  = new point2f(40/2 + 10*2 + 80 , 20/2 + 10*3 + 20*2);
			public static final point2f cCenter    = new point2f(60/2 + 10        , 20/2 + 10*3 + 20*2);
			public static final point2f cxCenter   = new point2f(70/2 + 10        , 20/2 + 10*2 + 20);
			public static final point2f sxCenter   = new point2f(40/2 + 10*2 + 80 , 20/2 + 10*2 + 20);
			public static final point2f cyCenter   = new point2f(70/2 + 10        , 20/2 + 10);
			public static final point2f syCenter   = new point2f(40/2 + 10*2 + 80 , 20/2 + 10);
			
			public lbButton show, putx, puty, change, get, changex, setx, changey, sety;
			public boolean iscx = false, iscy = false, isc = false,
							issx = false, issy = false,
							isget = false, islistening = false,
							ismove = false, isrename = false;
			public point2f variable = new point2f();
			public NumberVariable x = null, y = null;
			
			@Override public char[] getTypeName() { return TYPENAME; }
			public PointVariable() { super();
				name = "unnamed vector"; size = new point2f(10*4 + 110, 10*10 + 20*9);
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				putx = new lbButton(this, pxCenter, lbGUIelement.rtetralsize, "putx");
				puty = new lbButton(this, pyCenter, lbGUIelement.rtetralsize, "puty");
				change = new lbButton(this, cCenter, lbGUIelement.rhekzalsize, "change");
				get = new lbButton(this, getCenter, lbGUIelement.rtetralsize, "get");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				changex = new lbButton(this, cxCenter, lbGUIelement.rhentalsize, "changex");
				setx = new lbButton(this, sxCenter, lbGUIelement.rtetralsize, "setx");
				changey = new lbButton(this, cyCenter, lbGUIelement.rhentalsize, "changey");
				sety = new lbButton(this, syCenter, lbGUIelement.rtetralsize, "sety");
			}
			public void deleteSelf() {
				if(listener != null) {
					listener.removeVariable(new AnyVariableListener.VariableRemovalMessage(listener, this, -1));
					listener = null;
				}
				if(x != null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 0)); }
				if(y != null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 0)); }
				owner.removedGUI.add(this);
			}

			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); show.lbUpdate(owner, start);
					putx.lbUpdate(owner, start); puty.lbUpdate(owner, start);
					change.lbUpdate(owner, start); get.lbUpdate(owner, start);
					pick.lbUpdate(owner, start);
					setx.lbUpdate(owner, start); changex.lbUpdate(owner, start);
					sety.lbUpdate(owner, start); changey.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); show.handleHower(owner, start);
				putx.handleHower(owner, start); puty.handleHower(owner, start);
				change.handleHower(owner, start); get.handleHower(owner, start);
				pick.handleHower(owner, start);
				setx.handleHower(owner, start); changex.handleHower(owner, start);
				sety.handleHower(owner, start); changey.handleHower(owner, start);

				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					iscx = false; iscy = false; isc = false;
					issx = false; issy = false;
					isget = false; islistening = false;
					ismove = false; isrename = false;
					return;
				}
				if(owner.Mhandle.left && !owner.Mhandle.leftReaded && isget) {
					if(x!=null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 0)); }
					if(y!=null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 0)); }
					variable.x = owner.mposWorld.x; variable.y = owner.mposWorld.y;
					isget = false; islistening = false; owner.islistening = false;
					if(listener != null)  { listener.onVariableUpdate(this); }
					return;
					
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(isget) {
						if(x!=null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 0)); }
						if(y!=null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 0)); }
						variable.x = owner.center.x; variable.y = owner.center.y;
						isget = false; islistening = false; owner.islistening = false;
						if(listener != null)  { listener.onVariableUpdate(this); }
						return;
					}
					if(iscx) { iscx = false; islistening = false; owner.islistening = false; return; }
					if(iscy) { iscy = false; islistening = false; owner.islistening = false; return; }
					if(isc) { isc = false; islistening = false; owner.islistening = false; return; }
					if(issx) {
						try {
							variable.x = Float.valueOf(new String(owner.command)).floatValue();
						} catch(Exception e) { owner.command = new char[0]; return; }
						if(x!=null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 1)); }
						if(listener != null)  { listener.onVariableUpdate(this); }
						owner.command = new char[0]; owner.isCommandListening = false;
						islistening = false; issx = false;
						return;
					}
					if(issy) {
						try {
							variable.y = Float.valueOf(new String(owner.command)).floatValue();
						} catch(Exception e) { owner.command = new char[0]; return; }
						if(y!=null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 2)); }
						if(listener != null)  { listener.onVariableUpdate(this); }
						owner.command = new char[0]; owner.isCommandListening = false;
						islistening = false; issy = false;
						return;
					}
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isget || issx || issy || isrename) { return; }
				
				if(isc) {
					if(owner.kbhandle.left) { variable.x -= owner.cspeed; }
					if(owner.kbhandle.right) { variable.x += owner.cspeed; }
					if(owner.kbhandle.up) { variable.y += owner.cspeed; }
					if(owner.kbhandle.down) { variable.y -= owner.cspeed; }
					if(listener != null)  { listener.onVariableUpdate(this); }
					return;
				}
				if(iscx) {
					if(owner.kbhandle.left) { variable.x -= owner.cspeed; }
					if(owner.kbhandle.right) { variable.x += owner.cspeed; }
					if(listener != null)  { listener.onVariableUpdate(this); }
					return;
				}
				if(iscy) {
					if(owner.kbhandle.left) { variable.y -= owner.cspeed; }
					if(owner.kbhandle.right) { variable.y += owner.cspeed; }
					if(listener != null)  { listener.onVariableUpdate(this); }
					return;
				}
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC);
				rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart);show.lbPaint(target, owner, wstart);
				putx.lbPaint(target, owner, wstart); puty.lbPaint(target, owner, wstart);
				get.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				changex.lbPaint(target, owner, wstart); setx.lbPaint(target, owner, wstart);
				changey.lbPaint(target, owner, wstart); sety.lbPaint(target, owner, wstart);
				change.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				char[] nchars = name.toCharArray(), vchars = ("x:"+variable.x+" y:"+variable.y).toCharArray();
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i vpos = owner.mapW2D(wstart.sumwith(valueStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				target.drawChars(nchars, 0, nchars.length, npos.x, npos.y);
				target.drawChars(vchars, 0, vchars.length, vpos.x, vpos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(!((lbButton.lbButtonToggle)show).istrue) { return; }
				target.setColor(owner.showC); point2i pos = owner.mapW2D(variable).toInt();
				target.fillRect(pos.x-owner.showS/2, pos.y-owner.showS/2, owner.showS, owner.showS);
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenVariable = this; return; }
				if(action.reason == putx) {
					if(x != null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 1)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					x = (NumberVariable) owner.chosenVariable;
					variable.x = x.variable; x.addListener(this);
					return;
				}
				if(action.reason == puty) {
					if(y != null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 2)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					y = (NumberVariable) owner.chosenVariable;
					variable.y = y.variable; y.addListener(this);
					return;
				}
				if(action.reason == delete) { deleteSelf(); return; }
				islistening = true;
				if(action.reason == get) { owner.islistening = true; isget = true; return; }
				if(action.reason == change) {
					owner.islistening = true; isc = true;
					if(x!=null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 0)); }
					if(y!=null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 0)); }
					return;
				}
				if(action.reason == changex) {
					owner.islistening = true; iscx = true;
					if(x!=null) { x.removeListener(new AnyVariableListener.VariableRemovalMessage(this, x, 1)); }
					return;
				}
				if(action.reason == changey) {
					owner.islistening = true; iscy = true;
					if(y!=null) { y.removeListener(new AnyVariableListener.VariableRemovalMessage(this, y, 2)); }
					return; 
				}
				if(action.reason == setx) { owner.isCommandListening = true; issx = true; return; }
				if(action.reason == sety) { owner.isCommandListening = true; issy = true; return; }
				if(action.reason == rename) { owner.isCommandListening = true; isrename = true; return; }
				if(action.reason == move) { owner.islistening = true; ismove = true; return; }
			}
			
			@Override
			public void onVariableUpdate(AnyVariable update) {
				if(update == x) { variable.x = x.variable; }
				if(update == y) { variable.y = y.variable; }
			}
			@Override
			public void removeVariable(AnyVariableListener.VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 1: x = null; break; case 2: y = null; break;
					} return;
				}
				if(removal.variable == x) { x = null; }
				if(removal.variable == y) { y = null; }
			}

		}
		
	}

	/*******************************************************************************************************************
	 * A GUI only for represending a fraction of the map.
	 * Can be moved, renamed, deleted, pickd or showen.
	 * Variables can only be put to. For changing variables user should make a point variable.
	 *******************************************************************************************************************/
	public static abstract class AnyField extends ParticleEngineGUI implements AnyVariableListener {
		public static interface AnyFieldTargettedOne {
			/*
			public static class MultyFieldTargettedOne implements AnyFieldTargettedOne {
				AnyField field = null;
				ArrayList<AnyFieldTargettedOne> targetteds = new ArrayList<AnyFieldTargettedOne>();
				
				public void create(AnyField field, AnyFieldTargettedOne nowner) {
					this.field = field;
					targetteds.add(field.targetted); targetteds.add(nowner);
					field.targetted = this;
				}
				@Override
				public void removeField(AnyField field) {
					for(AnyFieldTargettedOne cowner : targetteds) { cowner.removeField(field); }
					if(this.field == field) { this.field = null; }
				}
			}
			*/
			public void removeField(AnyField field);
		}
		
		AnyFieldTargettedOne targetted = null;
		
		public void removeFieldTargettedOne(AnyFieldTargettedOne toremove) {
			if(targetted == toremove) { targetted.removeField(this); targetted = null; return; }
			/*
			ArrayList<AnyFieldTargettedOne> targetteds = ((MultyFieldTargettedOne) targetted).targetteds;
			for(int i=0;i!=targetteds.size();i++) { if(targetteds.get(i) == toremove) {
				targetteds.get(i).removeField(this); targetteds.remove(i); return;
			} }
			*/
		}
		public void addFieldTargettedOne(AnyFieldTargettedOne ntargetted) {
			if(targetted == null) { targetted = ntargetted; return; }
			/*
			if(targetted.getClass() == MultyFieldTargettedOne.class) {
				((MultyFieldTargettedOne)targetted).targetteds.add(ntargetted);
			} else { new MultyFieldTargettedOne().create(this, ntargetted); }
			*/
		}
		public void deleteSelf() {
			if(targetted != null) { targetted.removeField(this); targetted = null; }
			owner.removedGUI.add(this);
		}

		public AnyField() { super(); }
		
		public abstract boolean isInside(point2f position);

		/*************************************************************************************************************************
		 * A simple class that only stores few variables for a plain rectange.
		 *************************************************************************************************************************/
		public static class PlainField extends AnyField {

			public static final point2f rsize = new point2f(10*3 + 160, 10*10 + 20*9);
			public static final char[] TYPENAME = "Field: PLAIN RECTANGLE".toCharArray();

			public static final point2f typeStart    = new point2f(20               ,   8  + 10*9 + 20*8);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*8 + 20*7);
			public static final point2f moveCenter   = new point2f(50/2 + 10   +  5 , 20/2 + 10*7 + 20*6);
			public static final point2f pickCenter   = new point2f(40/2 + 10*2 + 55 , 20/2 + 10*7 + 20*6);
			public static final point2f showCenter   = new point2f(50/2 + 10*3 + 95 , 20/2 + 10*7 + 20*6);
			public static final point2f renameCenter = new point2f(60/2 + 20        , 20/2 + 10*6 + 20*5);
			public static final point2f deleteCenter = new point2f(60/2 + 20*2 + 70 , 20/2 + 10*6 + 20*5);
			public static final point2f centerStart  = new point2f(       10        ,  18  + 10*5 + 20*4);
			public static final point2f xextendStart = new point2f(       10        ,  18  + 10*4 + 20*3);
			public static final point2f yextendStart = new point2f(       10        ,  18  + 10*3 + 20*2);
			public static final point2f putcCenter   = new point2f(80/2 + 10   + 40 , 20/2 + 10*2 + 20*1);
			public static final point2f putxeCenter  = new point2f(80/2 + 10   +  0 , 20/2 + 10*1 + 20*0);
			public static final point2f putyeCenter  = new point2f(80/2 + 10*2 + 80 , 20/2 + 10*1 + 20*0);
			

			public lbButton putCenter, putXextend, putYextend, show, pick;
			public boolean  islistening = false, ismove = false, isrename = false;

			public PointVariable fieldCenter = null;
			public NumberVariable xExtend = null, yExtend = null;
			
			@Override public char[] getTypeName() { return TYPENAME; }
			
			@Override
			public boolean isInside(point2f position) {
				if(fieldCenter == null || xExtend == null || yExtend == null) { return false; }
				if(Math.abs(position.x - fieldCenter.variable.x) > xExtend.variable) { return false; }
				if(Math.abs(position.y - fieldCenter.variable.y) > yExtend.variable) { return false; }
				return true;
			}
			
			public PlainField() { super();
				name = "unnamed Plain Field"; size = new point2f(10*3 + 160, 10*10 + 20*9);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				putCenter = new lbButton(this, putcCenter, lbGUIelement.roctalsize, "put Center");
				putXextend = new lbButton(this, putxeCenter, lbGUIelement.roctalsize, "put x Extend");
				putYextend = new lbButton(this, putyeCenter, lbGUIelement.roctalsize, "put y Extend");
			}
			@Override
			public void deleteSelf() {
				if(targetted != null) { targetted.removeField(this); targetted = null; }
				if(xExtend != null) { xExtend.removeListener(new VariableRemovalMessage(this, xExtend, 1)); }
				if(yExtend != null) { yExtend.removeListener(new VariableRemovalMessage(this, yExtend, 2)); }
				if(fieldCenter != null) { fieldCenter.removeListener(new VariableRemovalMessage(this, fieldCenter, 3)); }
				owner.removedGUI.add(this);
			}
			
			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					putCenter.lbUpdate(owner, start); show.lbUpdate(owner, start);
					putXextend.lbUpdate(owner, start); putYextend.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				putCenter.handleHower(owner, start); show.handleHower(owner, start);
				putXextend.handleHower(owner, start); putYextend.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isrename) { return; }
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenField = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putCenter) {
					if(fieldCenter != null) { fieldCenter.removeListener(new VariableRemovalMessage(this, fieldCenter, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					fieldCenter = (PointVariable) owner.chosenVariable;
					fieldCenter.addListener(this);
					return;
				}
				if(action.reason == putXextend) {
					if(xExtend != null) { xExtend.removeListener(new VariableRemovalMessage(this, xExtend, 1)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					xExtend = (NumberVariable) owner.chosenVariable;
					xExtend.addListener(this);
					return;
				}
				if(action.reason == putYextend) {
					if(yExtend != null) { yExtend.removeListener(new VariableRemovalMessage(this, yExtend, 2)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					yExtend = (NumberVariable) owner.chosenVariable;
					yExtend.addListener(this);
					return;
				}
				islistening = true;
				if(action.reason == move) { ismove = !ismove; return; }
				if(action.reason == rename) { isrename = true; owner.isCommandListening = true; return; }
			}
			
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC);
				rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				putCenter.lbPaint(target, owner, wstart); show.lbPaint(target, owner, wstart);
				putXextend.lbPaint(target, owner, wstart); putYextend.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(fieldCenter != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(centerStart)).toInt(); chars = fieldCenter.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+fieldCenter.variable.x+" y:"+fieldCenter.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(xExtend != null) {
					point2i xpos = owner.mapW2D(wstart.sumwith(xextendStart)).toInt(); chars = xExtend.name.toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, xpos.y);
					chars = ("width:"+(xExtend.variable*2)).toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, (int)(xpos.y+15*owner.zoomed));
					
				}
				if(yExtend != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(yextendStart)).toInt(); chars = yExtend.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("height:"+(yExtend.variable*2)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
					
				}
				if(!((lbButton.lbButtonToggle)show).istrue || fieldCenter == null || xExtend == null || yExtend == null) { return; }
				target.setColor(owner.showC);
				point2i fstart = new point2f(-xExtend.variable*owner.zoomed, -yExtend.variable*owner.zoomed)
						.increase(owner.mapW2D(fieldCenter.variable)).toInt();
				target.drawRect(fstart.x, fstart.y, (int)(xExtend.variable*2*owner.zoomed), (int)(yExtend.variable*2*owner.zoomed));
			}

			@Override public void onVariableUpdate(AnyVariable update) { }
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 1: xExtend = null; break; case 2: yExtend = null; break; case 3: fieldCenter = null; break;
					} return;
				}
				if(removal.variable == xExtend) { xExtend = null; }
				if(removal.variable == yExtend) { yExtend = null; }
				if(removal.variable == fieldCenter) { yExtend = null; }
			}

		}
		
	}


	public static abstract class AnyAudience extends ParticleEngineGUI {
		public static interface AnyAudienceTargettedOne {
			/*
			public static class MultyFieldTargettedOne implements AnyAudienceTargettedOne {
				AnyField field = null;
				ArrayList<AnyFieldTargettedOne> targetteds = new ArrayList<AnyFieldTargettedOne>();
				
				public void create(AnyField field, AnyFieldTargettedOne nowner) {
					this.field = field;
					targetteds.add(field.targetted); targetteds.add(nowner);
					field.targetted = this;
				}
				@Override
				public void removeField(AnyField field) {
					for(AnyFieldTargettedOne cowner : targetteds) { cowner.removeField(field); }
					if(this.field == field) { this.field = null; }
				}
			}
			*/
			public void removeAudience(AnyAudience field);
		}
		
		AnyAudienceTargettedOne targetted = null;
		
		public void removeTargettedOne(AnyAudienceTargettedOne toremove) {
			if(targetted == toremove) { targetted.removeAudience(this); targetted = null; return; }
		}
		public void addTargettedOne(AnyAudienceTargettedOne ntargetted) {
			targetted = ntargetted;
		}
		public void deleteSelf() {
			if(targetted != null) { targetted.removeAudience(this); targetted = null; }
			owner.removedGUI.add(this);
		}


		public AnyAudience() { super(); }
		
		public abstract boolean isTarget(AnyParticleInstance particle);


		public static class FieldAudience extends AnyAudience implements AnyFieldTargettedOne {

			public static final point2f rsize = new point2f(10*3 + 160, 10*7 + 20*6);
			public static final char[] TYPENAME = "Audience: FIELD AUDIENCE".toCharArray();

			public static final point2f typeStart    = new point2f(20               ,   8  + 10*6 + 20*5);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*5 + 20*4);
			public static final point2f moveCenter   = new point2f(50/2 + 10   +  5 , 20/2 + 10*4 + 20*3);
			public static final point2f pickCenter   = new point2f(40/2 + 10*2 + 55 , 20/2 + 10*4 + 20*3);
			public static final point2f showCenter   = new point2f(50/2 + 10*3 + 95 , 20/2 + 10*4 + 20*3);
			public static final point2f renameCenter = new point2f(60/2 + 20        , 20/2 + 10*3 + 20*2);
			public static final point2f deleteCenter = new point2f(60/2 + 20*2 + 70 , 20/2 + 10*3 + 20*2);
			public static final point2f fieldStart   = new point2f(       10        ,  18  + 10*2 + 20*1);
			public static final point2f puttfCenter  = new point2f(80/2 + 10   + 40 , 20/2 + 10*1 + 20*0);
			

			public lbButton putTargetField, show, pick;
			public boolean  islistening = false, ismove = false, isrename = false;

			public AnyField targetField = null;

			@Override public char[] getTypeName() { return TYPENAME; }
			@Override
			public boolean isTarget(AnyParticleInstance particle) {
				if(targetField == null) { return false; }
				return targetField.isInside(particle.position);
			}
			
			public FieldAudience() { super();
				name = "unnamed Plain Field"; size = new point2f(10*3 + 160, 10*7 + 20*6);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				putTargetField = new lbButton(this, puttfCenter, lbGUIelement.rdecaTetransize, "put Target Field");
			}
			@Override
			public void deleteSelf() {
				if(targetted != null) { targetted.removeAudience(this); targetted = null; }
				targetField.removeFieldTargettedOne(this);
				owner.removedGUI.add(this);
			}

			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					putTargetField.lbUpdate(owner, start); show.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				putTargetField.handleHower(owner, start); show.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isrename) { return; }
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenAudience = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putTargetField) {
					if(targetField != null) { targetField.removeFieldTargettedOne(this); }
					if(owner.chosenField == null) { return; }
					if(!AnyField.class.isInstance(owner.chosenField.getClass())) { return; }
					targetField = owner.chosenField;
					targetField.addFieldTargettedOne(this);
					return;
				}
				islistening = true;
				if(action.reason == move) { ismove = !ismove; return; }
				if(action.reason == rename) { isrename = true; owner.isCommandListening = true; return; }
			}
			
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC);
				rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				putTargetField.lbPaint(target, owner, wstart); show.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(targetField != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(fieldStart)).toInt(); chars = targetField.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					
				}
				if(!((lbButton.lbButtonToggle)show).istrue) { return; }
				target.setColor(owner.showC);
			}

			@Override
			public void removeField(AnyField field) {
				if(field == targetField) { targetField = null; }
			}

		}

	}
	
	
	// make AnyInteractionType here
	
	/** TODO */
	/*
	public static abstract class AnySensor  extends ParticleEngineGUI{

		public AnySensor(ParticleEngine_v1_0 owner) { super(owner); }

		public float value = 0.f, request = 0.f;
		public boolean requested = false, drawStats = true;
		public point2i center, size;
		public ParticleEngine_v1_0 owner;
		public AnyAudience audience = null;
		
		public abstract void onStart();
		public abstract void iteration(AnyParticleInstance particle);
		public abstract void onEnd();
		public abstract void drawStrings2(Graphics2D target);
		public abstract void drawButtons2(Graphics2D target);
		public void drawBlock2(Graphics2D target) {
			point2i start = owner.mapW2D(new point2f(center.x, center.y)).toInt();
			start.x-=size.x/2; start.y-=size.y/2;
			target.fillRect(start.x, start.y, size.x, size.y);
		}
		
		
		public static class AverageHeatSensor extends AnySensor {
			public AverageHeatSensor(ParticleEngine_v1_0 owner) { super(owner); }
			public int x = 0, y = 0, w = 600, h = 600;
			public double heats = 0., masses = 0.;

			@Override public void onStart() { heats = 0.f; }
			@Override public void onEnd() { if(audience == null) { return; } value = (float) (heats / masses); }
			@Override public void iteration(AnyParticleInstance particle) {
				if(audience == null) { return; }
				if(!particle.type.moving) { return; }
				if(!audience.isTarget(particle)) { return; }
				masses += particle.getMass();
				heats += particle.vel.getamplitude();
			}
			@Override public void drawStrings2(Graphics2D target) {
				int sx = center.x - size.x/2, sy = center.y - size.y/2;
				int y = 10; char[] chars = null; //point2i pos = null;
				chars = "Heat Sensor".toCharArray();// pos = owner.mapW2D(new point2f(sx, sy)).toInt();
				target.drawChars(chars, 0, chars.length, sx+30, sy-y);
			}
			@Override public void drawButtons2(Graphics2D target) {
			}
			
		}
	}
	*/
	/** this ting is ok, FreeBorderLine class should be created */
	
	public static abstract class AnyBorderLine extends ParticleEngineGUI {
		public AnyBorderLine() { super(); }
		
		
		public abstract boolean isCollided(AnyParticleInstance particle);
		public abstract void repel(AnyParticleInstance particle);
		public void handle(AnyParticleInstance particle) {
			if(isCollided(particle)) { repel(particle); }
		}


		public static class HorrizontalRepellingBorderLine extends AnyBorderLine implements AnyVariableListener {

			public static final point2f rsize = new point2f(10*3 + 160, 10*11 + 20*10);
			public static final char[] TYPENAME = "Borderline: REPELLING H".toCharArray();

			public static final point2f typeStart    = new point2f(10               ,   8  + 10*10+ 20*9);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*9 + 20*8);
			public static final point2f moveCenter   = new point2f(50/2 + 10*1 +  5 , 20/2 + 10*8 + 20*7);
			public static final point2f pickCenter   = new point2f(40/2 + 10*2 + 55 , 20/2 + 10*8 + 20*7);
			public static final point2f showCenter   = new point2f(50/2 + 10*3 + 95 , 20/2 + 10*8 + 20*7);
			public static final point2f renameCenter = new point2f(60/2 + 20        , 20/2 + 10*7 + 20*6);
			public static final point2f deleteCenter = new point2f(60/2 + 20*2 + 70 , 20/2 + 10*7 + 20*6);
			public static final point2f centerStart  = new point2f(       10        ,  18  + 10*6 + 20*5);
			public static final point2f extendStart  = new point2f(       10        ,  18  + 10*5 + 20*4);
			public static final point2f widthStart   = new point2f(       10        ,  18  + 10*4 + 20*3);
			public static final point2f forceStart   = new point2f(       10        ,  18  + 10*3 + 20*2);
			public static final point2f putbcCenter  = new point2f(80/2 + 10        , 20/2 + 10*2 + 20*1);
			public static final point2f puteCenter   = new point2f(80/2 + 10*2 + 80 , 20/2 + 10*2 + 20*1);
			public static final point2f putwCenter   = new point2f(80/2 + 10        , 20/2 + 10*1 + 20*0);
			public static final point2f putfCenter   = new point2f(80/2 + 10*2 + 80 , 20/2 + 10*1 + 20*0);
			

			public lbButton putBorderCenter, putExtend, putWidth, putForce, show, pick;
			public boolean  islistening = false, ismove = false, isrename = false;

			public PointVariable borderCenter = null;
			public NumberVariable extend = null, force = null, width = null;

			@Override public char[] getTypeName() { return TYPENAME; }
			public HorrizontalRepellingBorderLine() { super();
				name = "a Horrizontal Border"; size = new point2f(10*3 + 160, 10*11 + 20*10);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				putBorderCenter = new lbButton(this, putbcCenter, lbGUIelement.roctalsize, "put Center");
				putExtend = new lbButton(this, puteCenter, lbGUIelement.roctalsize, "put Extend");
				putWidth = new lbButton(this, putwCenter, lbGUIelement.roctalsize, "put Width");
				putForce = new lbButton(this, putfCenter, lbGUIelement.roctalsize, "put Force");
			}
			@Override
			public void deleteSelf() {
				if(extend != null) { extend.removeListener(new VariableRemovalMessage(this, extend, 4)); }
				if(width != null) { width.removeListener(new VariableRemovalMessage(this, width, 1)); }
				if(force != null) { force.removeListener(new VariableRemovalMessage(this, force, 2)); }
				if(borderCenter != null) { borderCenter.removeListener(new VariableRemovalMessage(this, borderCenter, 3)); }
				if(borderCenter != null) { borderCenter.deleteSelf(); }
				owner.removedGUI.add(this);
			}

			@Override
			public void lbUpdate() {
				if(!owner.ispaused) { for(AnyParticleInstance particle : owner.allParticles) { handle(particle); } }

				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					putBorderCenter.lbUpdate(owner, start);
					putForce.lbUpdate(owner, start); show.lbUpdate(owner, start);
					putExtend.lbUpdate(owner, start); putWidth.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				putBorderCenter.handleHower(owner, start);
				putForce.handleHower(owner, start); show.handleHower(owner, start);
				putExtend.handleHower(owner, start); putWidth.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isrename) { return; }
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenBorderLine = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putBorderCenter) {
					if(borderCenter != null) { borderCenter.removeListener(new VariableRemovalMessage(this, borderCenter, 0)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					borderCenter = (PointVariable) owner.chosenVariable;
					borderCenter.addListener(this);
					return;
				}
				if(action.reason == putWidth) {
					if(width != null) { width.removeListener(new VariableRemovalMessage(this, width, 1)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					width = (NumberVariable) owner.chosenVariable;
					width.addListener(this);
					return;
				}
				if(action.reason == putForce) {
					if(force != null) { force.removeListener(new VariableRemovalMessage(this, force, 2)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					force = (NumberVariable) owner.chosenVariable;
					force.addListener(this);
					return;
				}
				if(action.reason == putExtend) {
					if(extend != null) { extend.removeListener(new VariableRemovalMessage(this, extend, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					extend = (NumberVariable) owner.chosenVariable;
					extend.addListener(this);
					return;
				}
				islistening = true;
				if(action.reason == move) { ismove = !ismove; owner.islistening = true; return; }
				if(action.reason == rename) { isrename = true; owner.isCommandListening = true; return; }
			}
			
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC); rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				putBorderCenter.lbPaint(target, owner, wstart);
				putForce.lbPaint(target, owner, wstart); show.lbPaint(target, owner, wstart);
				putExtend.lbPaint(target, owner, wstart); putWidth.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(borderCenter != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(centerStart)).toInt(); chars = borderCenter.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+borderCenter.variable.x+" y:"+borderCenter.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(extend != null) {
					point2i xpos = owner.mapW2D(wstart.sumwith(extendStart)).toInt(); chars = extend.name.toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, xpos.y);
					chars = ("extend:"+(extend.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, (int)(xpos.y+15*owner.zoomed));
				}
				if(width != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(widthStart)).toInt(); chars = width.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("width:"+(width.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(force != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(forceStart)).toInt(); chars = force.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("force:"+(force.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(!((lbButton.lbButtonToggle)show).istrue || borderCenter == null
						|| extend == null || width == null || force == null) { return; }
				dstart = owner.mapW2D(borderCenter.variable)
						.increase(-extend.variable*owner.zoomed/2.f, -width.variable*owner.zoomed/2.f).toInt();
				target.setColor(owner.showC);
				target.fillRect(dstart.x, dstart.y, (int)(extend.variable*owner.zoomed), (int)(width.variable*owner.zoomed));
			}

			@Override public void onVariableUpdate(AnyVariable update) { }
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 1: width = null; case 2: force = null;
					case 4: extend = null; break; case 3: borderCenter = null; break;
					} return;
				}
				if(removal.variable == extend) { extend = null; }
				if(removal.variable == force) { force = null; }
				if(removal.variable == width) { width = null; }
				if(removal.variable == borderCenter) { borderCenter = null; }
			}

			/* Depricated!
			public static class ParticlePointer {
				MoveableInstance particle; float entryVelocity;
				public ParticlePointer(MoveableInstance particle) {
					this.particle = particle; this.entryVelocity = particle.getVelocity().y;
				}
				public static ParticlePointer isInside(ArrayList<ParticlePointer> array, AnyParticleInstance instance) {
					if(instance.type.isMoveable()) { return null; }
					for(ParticlePointer particle : array) { if(particle.particle == instance) { return particle; } }
					return null;
				}
				public static void afterRepel(HorrizontalRepellingBorderLine owner, ArrayList<ParticlePointer> array) {
					for(int i=0;i!=array.size();i++) {
						ParticlePointer pp = array.get(i); MoveableInstance mi = pp.particle;
						if(pp.entryVelocity > 0.f) {
							if(mi.getVelocity().x < -pp.entryVelocity) { mi.getVelocity().x = -pp.entryVelocity; }
						} else {
							if(mi.getVelocity().x > -pp.entryVelocity) { mi.getVelocity().x = -pp.entryVelocity; }
						}
						if(!owner.isCollided((AnyParticleInstance) mi)) { array.remove(i); i--; }
					}
				}

			}
			ArrayList<ParticlePointer> array = new ArrayList<ParticlePointer>();
			*/
			
			@Override
			public boolean isCollided(AnyParticleInstance particle) {
				if(borderCenter == null || extend == null || width == null) { return false; }
				if(Math.abs(borderCenter.variable.x - particle.position.x) > extend.variable/2.f) { return false; }
				return Math.abs(borderCenter.variable.y - particle.position.y) < width.variable/2.f + particle.getRadius();
			}
			@Override
			public void repel(AnyParticleInstance particle) {
				if(!particle.type.isYmoving() || !particle.type.isMoving() || !particle.type.isMoveable()) { return; }
				float dist = Math.abs(particle.position.y - borderCenter.variable.y);
				point2f result = new point2f(); dist = dist < 1.f ? 1.f : dist;
				result.y = width.variable * force.variable * particle.getRadius() * particle.getToughness();
				float bd = dist - width.variable/2.f, pd = dist - particle.getRadius();
				bd = bd < 1.f ? 1.f : bd; pd = pd < 1.f ? 1.f : pd;
				result.y /= bd * pd * owner.frate;
				if(particle.position.y < borderCenter.variable.y) { result.y *= -1.f; }
				//ParticlePointer.
				((MoveableInstance)particle).aplyForce(result);
			}

		}
		public static class VerticalRepellingBorderLine extends AnyBorderLine implements AnyVariableListener {

			public static final point2f rsize = new point2f(10*3 + 160, 10*11 + 20*10);
			public static final char[] TYPENAME = "Borderline: REPELLING V".toCharArray();

			public static final point2f typeStart    = new point2f(10               ,   8  + 10*10+ 20*9);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*9 + 20*8);
			public static final point2f moveCenter   = new point2f(50/2 + 10*1 +  5 , 20/2 + 10*8 + 20*7);
			public static final point2f pickCenter   = new point2f(40/2 + 10*2 + 55 , 20/2 + 10*8 + 20*7);
			public static final point2f showCenter   = new point2f(50/2 + 10*3 + 95 , 20/2 + 10*8 + 20*7);
			public static final point2f renameCenter = new point2f(60/2 + 20        , 20/2 + 10*7 + 20*6);
			public static final point2f deleteCenter = new point2f(60/2 + 20*2 + 70 , 20/2 + 10*7 + 20*6);
			public static final point2f centerStart  = new point2f(       10        ,  18  + 10*6 + 20*5);
			public static final point2f extendStart  = new point2f(       10        ,  18  + 10*5 + 20*4);
			public static final point2f widthStart   = new point2f(       10        ,  18  + 10*4 + 20*3);
			public static final point2f forceStart   = new point2f(       10        ,  18  + 10*3 + 20*2);
			public static final point2f putbcCenter  = new point2f(80/2 + 10        , 20/2 + 10*2 + 20*1);
			public static final point2f puteCenter   = new point2f(80/2 + 10*2 + 80 , 20/2 + 10*2 + 20*1);
			public static final point2f putwCenter   = new point2f(80/2 + 10        , 20/2 + 10*1 + 20*0);
			public static final point2f putfCenter   = new point2f(80/2 + 10*2 + 80 , 20/2 + 10*1 + 20*0);
			

			public lbButton putBorderCenter, putExtend, putWidth, putForce, show, pick;
			public boolean  islistening = false, ismove = false, isrename = false;

			public PointVariable borderCenter = null;
			public NumberVariable extend = null, force = null, width = null;

			@Override public char[] getTypeName() { return TYPENAME; }
			public VerticalRepellingBorderLine() { super();
				name = "a Vertical Border"; size = new point2f(10*3 + 160, 10*11 + 20*10);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				putBorderCenter = new lbButton(this, putbcCenter, lbGUIelement.roctalsize, "put Center");
				putExtend = new lbButton(this, puteCenter, lbGUIelement.roctalsize, "put Extend");
				putWidth = new lbButton(this, putwCenter, lbGUIelement.roctalsize, "put Width");
				putForce = new lbButton(this, putfCenter, lbGUIelement.roctalsize, "put Force");
			}
			@Override
			public void deleteSelf() {
				if(extend != null) { extend.removeListener(new VariableRemovalMessage(this, extend, 4)); }
				if(width != null) { width.removeListener(new VariableRemovalMessage(this, width, 1)); }
				if(force != null) { force.removeListener(new VariableRemovalMessage(this, force, 2)); }
				if(borderCenter != null) { borderCenter.removeListener(new VariableRemovalMessage(this, borderCenter, 3)); }
				if(borderCenter != null) { borderCenter.deleteSelf(); }
				owner.removedGUI.add(this);
			}

			@Override
			public void lbUpdate() {
				if(!owner.ispaused) { for(AnyParticleInstance particle : owner.allParticles) { handle(particle); } }
				
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					putBorderCenter.lbUpdate(owner, start);
					putForce.lbUpdate(owner, start); show.lbUpdate(owner, start);
					putExtend.lbUpdate(owner, start); putWidth.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				putBorderCenter.handleHower(owner, start);
				putForce.handleHower(owner, start); show.handleHower(owner, start);
				putExtend.handleHower(owner, start); putWidth.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isrename) { return; }
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenBorderLine = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putBorderCenter) {
					if(borderCenter != null) { borderCenter.removeListener(new VariableRemovalMessage(this, borderCenter, 4)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					borderCenter = (PointVariable) owner.chosenVariable;
					borderCenter.addListener(this);
					return;
				}
				if(action.reason == putWidth) {
					if(width != null) { width.removeListener(new VariableRemovalMessage(this, width, 1)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					width = (NumberVariable) owner.chosenVariable;
					width.addListener(this);
					return;
				}
				if(action.reason == putForce) {
					if(force != null) { force.removeListener(new VariableRemovalMessage(this, force, 2)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					force = (NumberVariable) owner.chosenVariable;
					force.addListener(this);
					return;
				}
				if(action.reason == putExtend) {
					if(extend != null) { extend.removeListener(new VariableRemovalMessage(this, extend, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					extend = (NumberVariable) owner.chosenVariable;
					extend.addListener(this);
					return;
				}
				islistening = true;
				if(action.reason == move) { ismove = !ismove; owner.islistening = true; return; }
				if(action.reason == rename) { isrename = true; owner.isCommandListening = true; return; }
			}
			
			@Override
			public void lbPaint(Graphics2D target) {
				if(center == null) { return; }
				target.setColor(owner.guiC); rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				putBorderCenter.lbPaint(target, owner, wstart);
				putForce.lbPaint(target, owner, wstart); show.lbPaint(target, owner, wstart);
				putExtend.lbPaint(target, owner, wstart); putWidth.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(borderCenter != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(centerStart)).toInt(); chars = borderCenter.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+borderCenter.variable.x+" y:"+borderCenter.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(extend != null) {
					point2i xpos = owner.mapW2D(wstart.sumwith(extendStart)).toInt(); chars = extend.name.toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, xpos.y);
					chars = ("extend:"+(extend.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, (int)(xpos.y+15*owner.zoomed));
				}
				if(width != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(widthStart)).toInt(); chars = width.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("width:"+(width.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(force != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(forceStart)).toInt(); chars = force.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("force:"+(force.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(!((lbButton.lbButtonToggle)show).istrue || borderCenter == null
						|| extend == null || width == null || force == null) { return; }
				dstart = owner.mapW2D(borderCenter.variable)
						.increase(-width.variable*owner.zoomed/2.f, -extend.variable*owner.zoomed/2.f).toInt();
				target.setColor(owner.showC);
				target.fillRect(dstart.x, dstart.y, (int)(width.variable*owner.zoomed), (int)(extend.variable*owner.zoomed));
			}

			@Override public void onVariableUpdate(AnyVariable update) { }
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 1: width = null; case 2: force = null;
					case 3: extend = null; break; case 4: borderCenter = null; break;
					} return;
				}
				if(removal.variable == extend) { extend = null; }
				if(removal.variable == force) { force = null; }
				if(removal.variable == width) { width = null; }
				if(removal.variable == borderCenter) { borderCenter = null; }
			}

			/* Depricated!
			public static class ParticlePointer {
				MoveableInstance particle; float entryVelocity;
				public ParticlePointer(MoveableInstance particle) {
					this.particle = particle; this.entryVelocity = particle.getVelocity().x;
				}
				public static ParticlePointer isInside(ArrayList<ParticlePointer> array, AnyParticleInstance instance) {
					if(instance.type.isMoveable()) { return null; }
					for(ParticlePointer particle : array) { if(particle.particle == instance) { return particle; } }
					return null;
				}
				public static void afterRepel(HorrizontalRepellingBorderLine owner, ArrayList<ParticlePointer> array) {
					for(int i=0;i!=array.size();i++) {
						ParticlePointer pp = array.get(i); MoveableInstance mi = pp.particle;
						if(pp.entryVelocity > 0.f) {
							if(mi.getVelocity().x < -pp.entryVelocity) { mi.getVelocity().x = -pp.entryVelocity; }
						} else {
							if(mi.getVelocity().x > -pp.entryVelocity) { mi.getVelocity().x = -pp.entryVelocity; }
						}
						if(!owner.isCollided((AnyParticleInstance) mi)) { array.remove(i); i--; }
					}
				}

			}*/
			
			@Override
			public boolean isCollided(AnyParticleInstance particle) {
				if(borderCenter == null || extend == null || width == null) { return false; }
				if(Math.abs(borderCenter.variable.y - particle.position.y) > extend.variable) { return false; }
				return Math.abs(borderCenter.variable.x - particle.position.x) < width.variable/2.f + particle.getRadius();
			}
			@Override
			public void repel(AnyParticleInstance particle) {
				if(!particle.type.isXmoving() || !particle.type.isMoving()) { return; }
				float dist = Math.abs(particle.position.x - borderCenter.variable.x);
				point2f result = new point2f(); dist = dist < 1.f ? 1.f : dist;
				result.x = width.variable * force.variable * particle.getRadius() * particle.getToughness();
				float bd = dist - width.variable/2.f, pd = dist - particle.getRadius();
				bd = bd < 1.f ? 1.f : bd; pd = pd < 1.f ? 1.f : pd;
				result.x /= bd * pd * owner.frate;
				if(particle.position.x < borderCenter.variable.x) { result.x *= -1.f; }
				((MoveableInstance)particle).aplyForce(result);
			}

		}

	}

	public static abstract class AnyParticleType extends ParticleEngineGUI implements AnyVariableListener {
		public AnyParticleType() { super(); }
		public float radius = 10.f, mass = 1.f, toughness = 10.f;
		public point2f spawnCenter, spawnRadius = new point2f(100.f, 100.f);
		public ArrayList<AnyParticleInstance> instances = new ArrayList<AnyParticleInstance>(),
											pending = new ArrayList<AnyParticleInstance>(),
											removed = new ArrayList<AnyParticleInstance>();

		static point2f[] generateSummonPositions(int count, float distance, point2f spawnCenter) {
			point2f[] positions = new point2f[count];
			int squareWidth = 1, squareHeight = 1;
			while(squareWidth * squareWidth +0.1f< count) { squareWidth++; }
			while(squareWidth * squareHeight +0.1f< count) { squareHeight++; }
			float widthShift = squareWidth, heightShift = squareHeight;
			widthShift -= 1.f; widthShift /= 2.f; heightShift -= 1.f; heightShift /= 2.f;
			float xstart = -widthShift * distance, ystart = -heightShift * distance;
			xstart += spawnCenter.x; ystart += spawnCenter.y;
			for(int i=0;i!=count;i++) {
				positions[i] = new point2f((int)(xstart +(i%squareWidth)*distance), (int)(ystart +(i/(int)(squareWidth))*distance));
			}
			return positions;
		}
		
		public void handlePendings() {
			for(int i = 0; i != pending.size(); i++) {
				instances.add(pending.get(i));
				owner.allParticles.add(pending.get(i));
			} pending = new ArrayList<AnyParticleInstance>();
			for(int i = 0; i != removed.size(); i++) {
				instances.remove(removed.get(i));
				owner.allParticles.remove(removed.get(i));
			} removed = new ArrayList<AnyParticleInstance>();
		}
		
		public void spawnParticles(int count) {}
		
		public void spawnParticle(point2f position) {  }
		
		public abstract boolean isShow();
		public abstract boolean isMoveable(); public abstract boolean isMoving();
		public abstract boolean isXmoving(); public abstract boolean isYmoving();
		public abstract boolean isCollidingST(); public abstract boolean isCollidingOT();
		
		public static class BasicParticleType extends AnyParticleType {

			public static final point2f rsize = new point2f(230, 10*16 + 20*15);
			public static final char[] TYPENAME = "Particle Type: BASIC".toCharArray();

			public static final point2f typeStart      = new point2f(10   + 10*4      ,   8  + 10*15 + 20*14);
			public static final point2f nameStart      = new point2f(5    + 10*2      ,   8  + 10*14 + 20*13);
			
			public static final point2f moveCenter     = new point2f(50/2 + 10*2 + 15 , 20/2 + 10*13 + 20*12);
			public static final point2f pickCenter     = new point2f(40/2 + 10*3 + 65 , 20/2 + 10*13 + 20*12);
			public static final point2f showCenter     = new point2f(50/2 + 10*4 +105 , 20/2 + 10*13 + 20*12);
			public static final point2f renameCenter   = new point2f(60/2 + 10*1 +  5 , 20/2 + 10*12 + 20*11);
			public static final point2f spawnTCenter   = new point2f(60/2 + 10*2 + 65 , 20/2 + 10*12 + 20*11);
			public static final point2f deleteCenter   = new point2f(50/2 + 10*3 +125 , 20/2 + 10*12 + 20*11);
			
			public static final point2f spawnStart     = new point2f(       10        ,  18  + 10*11 + 20*10);
			public static final point2f radiusStart    = new point2f(       10        ,  18  + 10*10 + 20*9);
			public static final point2f massStart      = new point2f(       10        ,  18  + 10*9 + 20*8);
			public static final point2f toughnessStart = new point2f(       10        ,  18  + 10*8 + 20*7);
			public static final point2f spawnCstart    = new point2f(       10        ,  18  + 10*7 + 20*6);
			
			public static final point2f putposCenter   = new point2f(120/2+  5*2      , 20/2 + 10*6 + 20*5);
			public static final point2f putrCenter     = new point2f(80/2 +  5*4 +120 , 20/2 + 10*6 + 20*5);
			public static final point2f putcountCenter = new point2f(120/2+  5*2      , 20/2 + 10*5 + 20*4);
			public static final point2f putmCenter     = new point2f(70/2 +  5*4 +120 , 20/2 + 10*5 + 20*4);
			public static final point2f puttCenter     = new point2f(110/2+  5*0 + 60 , 20/2 + 10*4 + 20*3);
			
			public static final point2f movingCenter   = new point2f(60/2 +  5*2 + 00 , 20/2 + 10*3 + 20*2);
			public static final point2f xmovingCenter  = new point2f(70/2 +  5*3 + 60 , 20/2 + 10*3 + 20*2);
			public static final point2f ymovingCenter  = new point2f(70/2 +  5*4 +130 , 20/2 + 10*3 + 20*2);
			public static final point2f collSTcenter   = new point2f(130/2+  5*0 + 50 , 20/2 + 10*2 + 20*1);
			public static final point2f collOTcenter   = new point2f(130/2+  5*0 + 50 , 20/2 + 10*1 + 20*0);
			

			public lbButton spawn, putSpawnPositiont, putMass, putRadius, putToughness, putSpawnCount, show, pick;
			public lbButton.lbButtonToggle collidingSTb, collidingOTb, movingB, xmovingB, ymovingB;
			public boolean  islistening = false, ismove = false, isrename = false, deletePending = false;

			public PointVariable spawnPosition = null;
			public NumberVariable massV = null, radiusV = null, toughnessV = null, spawnCountV = null;

			@Override public char[] getTypeName() { return TYPENAME; }
			public BasicParticleType() { super();
				name = "a Particle Type"; size = new point2f(230, 10*16 + 20*15);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
				show = new lbButton.lbButtonToggle(this, showCenter, lbGUIelement.rpentalsize, "show");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				spawn = new lbButton(this, spawnTCenter, lbGUIelement.rhekzalsize, "spawn");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rpentalsize, "delete");
				putSpawnPositiont = new lbButton(this, putposCenter, lbGUIelement.rdecadiensize, "put Spawn Center");
				putSpawnCount = new lbButton(this, putcountCenter, lbGUIelement.rdecadiensize, "put Spawn Count");
				putMass = new lbButton(this, putmCenter, lbGUIelement.rhentalsize, "put Mass");
				putToughness = new lbButton(this, puttCenter, lbGUIelement.rdecamonansize, "put Toughness");
				putRadius = new lbButton(this, putrCenter, lbGUIelement.roctalsize, "put Radius");
				movingB = new lbButton.lbButtonToggle(this, movingCenter, lbGUIelement.rhekzalsize, "moving");
				xmovingB = new lbButton.lbButtonToggle(this, xmovingCenter, lbGUIelement.rhentalsize, "moving X");
				ymovingB = new lbButton.lbButtonToggle(this, ymovingCenter, lbGUIelement.rhentalsize, "moving Y");
				collidingSTb = new lbButton.lbButtonToggle(this, collSTcenter, lbGUIelement.rdecatriensize, "colliding same types");
				collidingOTb = new lbButton.lbButtonToggle(this, collOTcenter, lbGUIelement.rdecatriensize, "colliding other types");
				movingB.istrue = true; xmovingB.istrue = true; ymovingB.istrue = true;
				collidingSTb.istrue = true; collidingOTb.istrue = true;
			}
			@Override
			public void deleteSelf() {
				pending = new ArrayList<AnyParticleInstance>();
				if(massV != null) { massV.removeListener(new VariableRemovalMessage(this, massV, 2)); }
				if(radiusV != null) { radiusV.removeListener(new VariableRemovalMessage(this, radiusV, 3)); }
				if(toughnessV != null) { toughnessV.removeListener(new VariableRemovalMessage(this, toughnessV, 4)); }
				if(spawnPosition != null) { spawnPosition.removeListener(new VariableRemovalMessage(this, spawnPosition, 1)); }
				for(int i = 0; i != instances.size(); i++) { removed.add(instances.get(i)); }
				deletePending = true;
			}

			public void summon(int count) {
				point2f[] positions = generateSummonPositions(count, radius*2.f, spawnCenter);
				for(point2f position : positions) {
					BasicParticleInstance nparticle = new BasicParticleInstance(this);
					nparticle.position = position; pending.add(nparticle); 
				}
			}

			@Override
			public void lbUpdate() {
				if(deletePending) { if(removed.size() == 0) {
					owner.removedGUI.add(this);
				} }
				
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					putSpawnPositiont.lbUpdate(owner, start);
					putSpawnCount.lbUpdate(owner, start);
					putToughness.lbUpdate(owner, start); show.lbUpdate(owner, start);
					putMass.lbUpdate(owner, start); putRadius.lbUpdate(owner, start);
					xmovingB.lbUpdate(owner, start); ymovingB.lbUpdate(owner, start);
					spawn.lbUpdate(owner, start); movingB.lbUpdate(owner, start);
					collidingSTb.lbUpdate(owner, start);
					collidingOTb.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); pick.handleHower(owner, start);
				putSpawnPositiont.handleHower(owner, start);
				putSpawnCount.handleHower(owner, start);
				putToughness.handleHower(owner, start); show.handleHower(owner, start);
				putMass.handleHower(owner, start); putRadius.handleHower(owner, start);
				xmovingB.handleHower(owner, start); ymovingB.handleHower(owner, start);
				spawn.handleHower(owner, start); movingB.handleHower(owner, start);
				collidingSTb.handleHower(owner, start);
				collidingOTb.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(ismove) { ismove = false; islistening = false; owner.islistening = false; return; }
					if(isrename) {
						name = new String(owner.command);
						owner.command = new char[0];
						owner.isCommandListening = false;
						islistening = false; isrename = false;
						return;
					}
				}
				if(isrename) { return; }
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == show) { show.acceptClick(); return; }
				if(action.reason == movingB) { movingB.acceptClick(); return; }
				if(action.reason == xmovingB) { xmovingB.acceptClick(); return; }
				if(action.reason == ymovingB) { ymovingB.acceptClick(); return; }
				if(action.reason == collidingSTb) { collidingSTb.acceptClick(); return; }
				if(action.reason == collidingOTb) { collidingOTb.acceptClick(); return; }
				if(action.reason == pick) { owner.chosenParticleType = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == spawn) {
					if(spawnPosition == null) { return; }
					if(spawnCountV != null) { summon((int)spawnCountV.variable); return; }
					pending.add(new BasicParticleInstance(this, new point2f(spawnPosition.variable)));
					return;
				}
				if(action.reason == putSpawnPositiont) {
					if(spawnPosition != null) { spawnPosition.removeListener(new VariableRemovalMessage(this, spawnPosition, 1)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					spawnPosition = (PointVariable) owner.chosenVariable;
					spawnCenter = new point2f(spawnPosition.variable);
					spawnPosition.addListener(this);
					return;
				}
				if(action.reason == putSpawnCount) {
					if(spawnCountV != null) { spawnCountV.removeListener(new VariableRemovalMessage(this, spawnPosition, 5)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					spawnCountV = (NumberVariable) owner.chosenVariable;
					spawnCountV.addListener(this);
					return;
				}
				if(action.reason == putRadius) {
					if(radiusV != null) { radiusV.removeListener(new VariableRemovalMessage(this, radiusV, 2)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					radiusV = (NumberVariable) owner.chosenVariable;
					radiusV.addListener(this); radius = radiusV.variable;
					return;
				}
				if(action.reason == putToughness) {
					if(toughnessV != null) { toughnessV.removeListener(new VariableRemovalMessage(this, toughnessV, 4)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					toughnessV = (NumberVariable) owner.chosenVariable;
					toughnessV.addListener(this); toughness = toughnessV.variable;
					return;
				}
				if(action.reason == putMass) {
					if(massV != null) { massV.removeListener(new VariableRemovalMessage(this, massV, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != NumberVariable.class) { return; }
					massV = (NumberVariable) owner.chosenVariable;
					massV.addListener(this); mass = massV.variable;
					return;
				}
				islistening = true;
				if(action.reason == move) { ismove = !ismove; owner.islistening = true; return; }
				if(action.reason == rename) { isrename = true; owner.isCommandListening = true; return; }
			}
			
			@Override
			public void lbPaint(Graphics2D target) {
				if(center == null) { return; }
				target.setColor(owner.guiC); rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				rename.lbPaint(target, owner, wstart); move.lbPaint(target, owner, wstart);
				delete.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				putSpawnPositiont.lbPaint(target, owner, wstart);
				putToughness.lbPaint(target, owner, wstart); show.lbPaint(target, owner, wstart);
				putMass.lbPaint(target, owner, wstart); putRadius.lbPaint(target, owner, wstart);
				spawn.lbPaint(target, owner, wstart); putSpawnCount.lbPaint(target, owner, wstart);
				collidingSTb.lbPaint(target, owner, wstart);
				collidingOTb.lbPaint(target, owner, wstart);
				movingB.lbPaint(target, owner, wstart);
				xmovingB.lbPaint(target, owner, wstart); ymovingB.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(spawnPosition != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(spawnStart)).toInt(); chars = spawnPosition.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+spawnPosition.variable.x+" y:"+spawnPosition.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(massV != null) {
					point2i xpos = owner.mapW2D(wstart.sumwith(massStart)).toInt(); chars = massV.name.toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, xpos.y);
					chars = ("mass:"+massV.variable).toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, (int)(xpos.y+15*owner.zoomed));
				}
				if(radiusV != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(radiusStart)).toInt(); chars = radiusV.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("radius:"+(radiusV.variable*2)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(toughnessV != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(toughnessStart)).toInt(); chars = toughnessV.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("toughness:"+(toughnessV.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
				if(spawnCountV != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(spawnCstart)).toInt(); chars = spawnCountV.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					chars = ("Spawn Count:"+(spawnCountV.variable)).toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, (int)(ypos.y+15*owner.zoomed));
				}
			}

			@Override public void onVariableUpdate(AnyVariable update) {
				if(update == spawnPosition) { spawnCenter = new point2f(spawnPosition.variable); return; }
				if(update == massV) { mass = massV.variable; }
				if(update == radiusV) { radius= radiusV.variable; }
				if(update == toughnessV) { toughness = toughnessV.variable; }
			}
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 3: radiusV = null; case 4: toughnessV = null;
					case 2: massV = null; break; case 1: spawnPosition = null; break;
					case 5: spawnCountV = null; break;
					} return;
				}
				if(removal.variable == massV) { massV = null; }
				if(removal.variable == toughnessV) { toughnessV = null; }
				if(removal.variable == radiusV) { radiusV = null; }
				if(removal.variable == spawnPosition) { spawnPosition = null; }
				if(removal.variable == spawnCountV) { spawnCountV = null; }
			}

			@Override public boolean isMoveable() { return true; }
			@Override public boolean isMoving() { return movingB.istrue; }
			@Override public boolean isXmoving() { return xmovingB.istrue; }
			@Override public boolean isYmoving() { return ymovingB.istrue; }
			@Override public boolean isCollidingST() { return collidingSTb.istrue; }
			@Override public boolean isCollidingOT() { return collidingOTb.istrue; }
			@Override public boolean isShow() { return ((lbButton.lbButtonToggle)show).istrue; }

		}
		public static class BeamingParticleType extends AnyParticleType {
			public static final point2f rsize = new point2f(10*3 + 160, 10*11 + 20*10);
			public static final char[] TYPENAME = "Particle Type: BASIC".toCharArray();
			
			
			public float beamPeriot = 1.f, minimumRadius = 10.f;
			@Override public char[] getTypeName() { return TYPENAME; }
			@Override
			public void deleteSelf() {
				// TODO Auto-generated method stub
				
			}

			@Override public boolean isMoveable() { return true; }

			@Override
			public void lbUpdate() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onlbGUIaction(lbGUIaction action) {
			}

			@Override
			public void lbPaint(Graphics2D target) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onVariableUpdate(AnyVariable update) {
			}
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
			}

			@Override
			public boolean isMoving() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isXmoving() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isYmoving() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isCollidingST() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isCollidingOT() {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public boolean isShow() {
				// TODO Auto-generated method stub
				return false;
			}

		}

	}
	
	public static abstract class AnyParticleInstance {
		
		static float handleFinity(float f, float middle, float extend) {
			if(Float.isFinite(f)) { return f; }
			return (float) (Math.random()*2.f*extend - extend + middle);
		}
		
		public AnyParticleType type;
		public point2f position = new point2f();
		
		public float getRadius() { return type.radius; }
		public double getMass() { return type.mass; }
		public float getToughness() { return type.toughness; }

		public void drawCircle(Graphics2D target, ParticleEngine_v1 owner) {
			point2f dpos = owner.mapW2D(this.position); float r = getRadius() * owner.zoomed;
			target.drawOval((int)(dpos.x-r), (int)(dpos.y-r), (int)(r*2.f),(int)(r*2.f));
		}
		public void drawPosition(Graphics2D target, ParticleEngine_v1 owner) {
			if(!type.isShow()) { return; }
			point2f dpos = owner.mapW2D(this.position);
			target.fillRect((int)(dpos.x-owner.showS/2), (int)(dpos.y-owner.showS/2), owner.showS, owner.showS);
		}
		public void drawBall(Graphics2D target, ParticleEngine_v1 owner) {
			point2f dpos = owner.mapW2D(this.position); float r = getRadius() * owner.zoomed;
			target.setColor(new Color(63, 127, 63));
			target.fillOval( (int)(dpos.x-r), (int)(dpos.y-r), (int)(r*2.f+1.f),(int)(r*2.f));
		}
		

		public boolean isCollided(AnyParticleInstance other, ParticleEngine_v1 owner) {
			if(other.type == type) { if(!type.isCollidingST()) { return false; }
			} else { if(!other.type.isCollidingOT() || !type.isCollidingOT()) { return false; } }
			return other.position.minus().increase(position).getamplitude() <= getRadius() + other.getRadius();
		}
		public void repel(AnyParticleInstance other, ParticleEngine_v1 owner) {
			point2f forge = position.minus().increase(other.position);
			float dist = forge.getamplitude();
			float sdist = dist - getRadius(), odist = dist - other.getRadius();
			sdist = sdist < 1.f ? 1.f : sdist; odist = odist < 1.f ? 1.f : odist;
			forge.setamplitude(getRadius()*getToughness()*other.getRadius()*other.getToughness()/(sdist * odist * owner.frate));
			if(other.type.isMoving()) { ((MoveableInstance)other).aplyForce(forge); }
		}
		
		public void handleFinity() {
			position.x = AnyParticleInstance.handleFinity(position.x, type.spawnCenter.x, type.spawnRadius.x);
			position.y = AnyParticleInstance.handleFinity(position.y, type.spawnCenter.y, type.spawnRadius.y);
		}
		
		public abstract void lbUpdate(ParticleEngine_v1 owner);
		
		public static interface MoveableInstance {
			public void aplyForce(point2f force);
			public point2f getVelocity();
		}
		
		
		public static class BasicParticleInstance extends AnyParticleInstance implements MoveableInstance {
			
			public point2f vel = new point2f(), forces = new point2f();
			
			public BasicParticleInstance(BasicParticleType type) { super.type = type; position = new point2f(); vel = new point2f(); }
			public BasicParticleInstance(BasicParticleType type, point2f position) {
				this.type = type; super.position = new point2f(position); vel = new point2f();
			}
			public BasicParticleInstance(BasicParticleType type, point2f position, point2f velocity) {
				this.type = type; super.position = new point2f(position); vel = new point2f(velocity);
			}
			
			@Override public void aplyForce(point2f force) { forces.increase(force); }
			@Override public point2f getVelocity() { return vel; }

			@Override public void lbUpdate(ParticleEngine_v1 owner) {
				for(AnyParticleInstance other : owner.allParticles) {
					if(other == this) { continue; }
					if(!isCollided(other, owner)) { continue; }
					repel(other, owner);
				}
				if(!type.isMoving()) { forces = new point2f(); return; }
				// Ending here!
				forces.amplifie((float)(1.f/(getMass()*owner.frate)));
				if(type.isXmoving()) { vel.x += forces.x; position.x += vel.x * owner.oneOverFrate; }
				if(type.isYmoving()) { vel.y += forces.y; position.y += vel.y * owner.oneOverFrate; }
				forces = new point2f();
			}
			
			public void drawBall(Graphics2D target, ParticleEngine_v1 owner) {
				point2f dpos = owner.mapW2D(this.position); float r = getRadius() * owner.zoomed;
				int heat = (int)((vel.getamplitude()*5.1f/3.f-255.f)); boolean b = heat < 0;
				if(b) { heat *= -1.f; } heat = heat > 255 ? 255 : heat;
				if(b) {
					target.setColor(new Color(127-heat/2, 63-heat/4, 127+heat/2));
				} else {
					target.setColor(new Color(127+heat/2, 63-heat/4, 127-heat/2));
				}
				target.fillOval( (int)(dpos.x-r), (int)(dpos.y-r), (int)(r*2.f+1.f),(int)(r*2.f));
			}
			
			@Override
			public void handleFinity() {
				position.x = AnyParticleInstance.handleFinity(position.x, type.spawnCenter.x, type.spawnRadius.x);
				position.y = AnyParticleInstance.handleFinity(position.y, type.spawnCenter.y, type.spawnRadius.y);
				float nx = AnyParticleInstance.handleFinity(vel.x, 10.f, 0.f);
				float ny = AnyParticleInstance.handleFinity(vel.y, 10.f, 0.f);
				if(vel.x == nx && vel.y == ny) { return; }
				vel.x = nx; vel.y = ny;
				while(vel.x==0.f&&vel.y==0.f) {
					vel.x = (float) (Math.random() * (20) -10);
					vel.y = (float) (Math.random() * (20) -10);
				}
				vel.setamplitude(15.f); forces = new point2f();
			}
			@Override
			public void repel(AnyParticleInstance other, ParticleEngine_v1 owner) {
				point2f forge = position.minus().increase(other.position);
				float dist = forge.getamplitude();
				float sdist = dist - getRadius(), odist = dist - other.getRadius();
				sdist = sdist < 1.f ? 1.f : sdist; odist = odist < 1.f ? 1.f : odist;
				forge.setamplitude(getRadius()*getToughness()*other.getRadius()*other.getToughness()/(sdist * odist * owner.frate));
				forces.increase(forge.minus());
				if(other.type.isMoving()) { ((MoveableInstance)other).aplyForce(forge); }
			}

		}
		public static class BeamingInstance extends AnyParticleInstance implements MoveableInstance {
			public point2f vel = new point2f(), forces = new point2f();
			public float step = 0.f, radiusAmplitude = 0.f;

			@Override public void aplyForce(point2f force) { forces.increase(force); }
			@Override public float getRadius() { return type.radius * radiusAmplitude + ((BeamingParticleType)type).minimumRadius; }

			@Override
			public void lbUpdate(ParticleEngine_v1 owner) {
				BeamingParticleType mtype = (BeamingParticleType) type;
				step += owner.oneOverFrate; if(step >= mtype.beamPeriot) { step -= mtype.beamPeriot; }
				radiusAmplitude = (float) ((Math.sin(step * 2.f * Math.PI / mtype.beamPeriot) + 1.f) / 2.f);
				for(AnyParticleInstance other : owner.allParticles) {
					if(other == this) { continue; }
					if(!isCollided(other, owner)) { continue; }
					repel(other, owner);
				}
				
				// Ending here!
				forces.amplifie((float)(1.f/(getMass()*owner.frate)));
				if(type.isXmoving()) { vel.x += forces.x; position.x += vel.x * owner.oneOverFrate; }
				if(type.isYmoving()) { vel.y += forces.y; position.y += vel.y * owner.oneOverFrate; }
				forces = new point2f();
			}

			@Override
			public void drawBall(Graphics2D target, ParticleEngine_v1 owner) {
				point2f dpos = owner.mapW2D(this.position); float r = getRadius() * owner.zoomed;
				int heat = (int)((vel.getamplitude()*5.1f/3.f-255.f)); boolean b = heat < 0;
				if(b) { heat *= -1.f; } heat = heat > 255 ? 255 : heat;
				if(b) {
					target.setColor(new Color(127-heat/2, 63-heat/4, 127+heat/2));
				} else {
					target.setColor(new Color(127+heat/2, 63-heat/4, 127-heat/2));
				}
				target.fillOval( (int)(dpos.x-r), (int)(dpos.y-r), (int)(r*2.f+1.f),(int)(r*2.f));
			}

			@Override
			public void handleFinity() {
				position.x = AnyParticleInstance.handleFinity(position.x, type.spawnCenter.x, type.spawnRadius.x);
				position.y = AnyParticleInstance.handleFinity(position.y, type.spawnCenter.y, type.spawnRadius.y);
				float nx = AnyParticleInstance.handleFinity(vel.x, 10.f, 0.f);
				float ny = AnyParticleInstance.handleFinity(vel.y, 10.f, 0.f);
				if(vel.x == nx && vel.y == ny) { return; }
				vel.x = nx; vel.y = ny;
				while(vel.x==0.f&&vel.y==0.f) {
					vel.x = (float) (Math.random() * (20) -10);
					vel.y = (float) (Math.random() * (20) -10);
				}
				vel.setamplitude(15.f); forces = new point2f();
			}
			@Override
			public void repel(AnyParticleInstance other, ParticleEngine_v1 owner) {
				point2f forge = position.minus().increase(other.position);
				float dist = forge.getamplitude();
				float sdist = dist - getRadius(), odist = dist - other.getRadius();
				sdist = sdist < 1.f ? 1.f : sdist; odist = odist < 1.f ? 1.f : odist;
				forge.setamplitude(getRadius()*getToughness()*other.getRadius()*other.getToughness()/(sdist * odist * owner.frate));
				forces.increase(forge.minus());
				if(other.type.isMoving()) { ((MoveableInstance)other).aplyForce(forge); }
			}
			@Override
			public point2f getVelocity() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		
	}
	
	public AnyVariable chosenVariable = null;
	public AnyField chosenField = null;
	public AnyAudience chosenAudience = null;
	public AnyBorderLine chosenBorderLine = null;
	//public AnySensor chosenSensor = null;
	public AnyParticleType chosenParticleType = null;
	public FileHandle chosenFile = null;

	public boolean islistening = false, isCommandListening = false, escaped = false, entered = false;
	public float controlMuliplier = 0.09f, shiftMuliplier = 15.f, defaultSpeed = 150.f, zoom = 16.f/15.f;
	public char[] command = new char[0];
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(isCommandListening) {
			char nchar = e.getKeyChar();
			if(nchar == KeyEvent.CHAR_UNDEFINED || nchar == '\n') { return; }
			if(nchar == '\b') {
				if(command.length == 0) { return; }
				char[] ncommand = new char[command.length-1];
				for(int i=0;i!=ncommand.length;i++) { ncommand[i] = command[i]; }
				command = ncommand; return;
			}
			char[] ncommand = new char[command.length+1];
			for(int i=0;i!=command.length;i++) { ncommand[i] = command[i]; }
			command = ncommand; command[command.length-1] = nchar;
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(isCommandListening) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				kbhandle.onKeyPress(e.getKeyCode());
			}
			return;
		}
		kbhandle.onKeyPress(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) { entered = false; }
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { escaped = false; }
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { owner.isSpaceRead = false; }
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { owner.isBackSpaceRead = false; }
		kbhandle.onKeyRelease(e.getKeyCode());
	}
	
	/** 0: simulation, 1: single line command requested, */
	public int state = 0;
	public int fdelay = 31;
	public float frate = 1.f, oneOverFrate = 1.f, simulationSpeed = 1.f;
	public float cspeed = defaultSpeed;
	public float getSimulationSpeed() { return cspeed * simulationSpeed; }
	public Aplication owner;
	public lbKeyBoardHandle kbhandle; public lbMouseHandle Mhandle;
	public int lbWidth() { if(owner.isMIopen) { return getWidth()-280; } else { return getWidth()-30; } }
	public int lbHeight() { return getHeight(); }
	public point2f mousepos = null, mposWorld = null;

	public lbMutex particleMutex = new lbMutex("Particle Mutex"), GUImutex = new lbMutex("GUI Mutex");
	
	public void handleParticles() {
		if(!isrunning || ispaused) { return; }
		particleMutex.lock();
		GUImutex.lock();
		for(AnyParticleType cpt : allTypes) { cpt.handlePendings(); }
		GUImutex.unlock();
		for(AnyParticleInstance cpi : allParticles) { cpi.handleFinity(); }
		for(AnyParticleInstance cpi : allParticles) { cpi.lbUpdate(this); }
		particleMutex.unlock();
	}
	public void lbUpdate() {
		if(!isrunning) { return; }
		cspeed = defaultSpeed * oneOverFrate;
		if(kbhandle.shift) { cspeed *= shiftMuliplier; }
		if(kbhandle.control) { cspeed *= controlMuliplier; }
		if(kbhandle.w) { center.y += cspeed; } if(kbhandle.s) { center.y -= cspeed; }
		if(kbhandle.d) { center.x += cspeed; } if(kbhandle.a) { center.x -= cspeed; }
		
		mousepos = Mhandle.getPosition(this); mposWorld = mapD2W(mousepos);
		
		particleMutex.lock();
		GUImutex.lock();
		handlePendings();
		particleMutex.unlock();
		for(ParticleEngineGUI gui : allGUI) { gui.lbUpdate(); }
		GUImutex.unlock();
	}
	
	public boolean isrunning = true, ispaused = false;
	public Timer utimer = new  Timer(), dtimer = new Timer(), ptimer = new Timer();
	public TimerTask utask = new TimerTask() { @Override public void run() { owner.lbUpdate(); } };
	public TimerTask dtask = new TimerTask() { @Override public void run() { owner.repaint(); } };
	public TimerTask ptask = new TimerTask() { @Override public void run() { handleParticles(); } };
	
	public ArrayList<AnyParticleInstance> allParticles = new ArrayList<AnyParticleInstance>();
	public ArrayList<ParticleEngineGUI> allGUI = new ArrayList<ParticleEngineGUI>();
	public ArrayList<AnyParticleType> allTypes = new ArrayList<AnyParticleType>();
	public ArrayList<AnyAudience> allAudienceTargets = new ArrayList<AnyAudience>();
	public ArrayList<AnyBorderLine> allBorderLines = new ArrayList<AnyBorderLine>();
	//public ArrayList<AnySensor> allSensors = new ArrayList<AnySensor>();
	public ArrayList<AnyVariable> allVariables = new ArrayList<AnyVariable>();

	public ArrayList<ParticleEngineGUI> pendingGUI = new ArrayList<ParticleEngineGUI>();
	public ArrayList<ParticleEngineGUI> removedGUI = new ArrayList<ParticleEngineGUI>();
	public ParticleEngineGUI toFront = null;
	
	public void handlePendings() {
		if(toFront != null) {
			int index = allGUI.indexOf(toFront);
			if(index == 0) { toFront = null; return; }
			for(int i=index;i!=0;i--) {
				allGUI.set(i, allGUI.get(i-1));
			}
			allGUI.set(0, toFront);
			toFront = null;
		}
		for (int i=0;i!=removedGUI.size();i++) {
			ParticleEngineGUI element = removedGUI.get(i);
			allGUI.remove(element);
			if(AnyParticleType.class.isInstance(element)) {
				allTypes.remove(element);
				if(chosenParticleType == element) { chosenParticleType = null; }
			}
			if(AnyAudience.class.isInstance(element)) {
				allAudienceTargets.remove(element);
				if(chosenAudience == element) { chosenAudience = null; }
			}
			if(AnyBorderLine.class == element.getClass()) {
				allBorderLines.remove(element);
				if(chosenBorderLine == element) { chosenBorderLine = null; }
			}
			//if(AnySensor.class.isInstance(element)) { allSensors.remove(element); }
			if(AnyVariable.class.isInstance(element)) {
				allVariables.remove(element);
				if(chosenVariable == element) { chosenVariable = null; }
			}
			if(AnyField.class.isInstance(element)) {
				if(chosenField == element) { chosenField = null; }
			}
		} removedGUI = new ArrayList<ParticleEngineGUI>();
		for (int i=0;i!=pendingGUI.size();i++) {
			ParticleEngineGUI element = pendingGUI.get(i);
			allGUI.add(element);
			if(AnyParticleType.class.isInstance(element)) { allTypes.add((AnyParticleType) element); }
			if(AnyAudience.class.isInstance(element)) { allAudienceTargets.add((AnyAudience) element); }
			if(AnyBorderLine.class.isInstance(element)) { allBorderLines.add((AnyBorderLine) element); }
			//if(AnySensor.class.isInstance(element)) { allSensors.add((AnySensor) element); }
			if(AnyVariable.class.isInstance(element)) { allVariables.add((AnyVariable) element); }
		} pendingGUI = new ArrayList<ParticleEngineGUI>();
	}
	

	public int showS = 5;
	public Color bgC = new Color(127, 127, 127), showC = new Color(0, 255, 0), guiC = new Color(31, 95, 31),
				buttonC = new Color(31, 31, 31), cursorC = new Color(255, 127, 255);
	
	float zoomed = 1.f;
	
	public void lbPaint(Graphics2D target) {
		zoomed = 1.f;
		if(Mhandle.wheel < 0) {
			int steps = -Mhandle.wheel; 
			for(int i=0;i!=steps;i++) { zoomed /= zoom; }
		} else if(Mhandle.wheel > 0) {
			int steps = Mhandle.wheel;
			for(int i=0;i!=steps;i++) { zoomed *= zoom; }
		}
		lbGUIelement.handleZoom(zoomed);
		
		zoomX = zoomed; zoomY = zoomed; Font tfont = target.getFont();
		Font nfont = new Font(tfont.getFamily(), tfont.getStyle(), (int)(tfont.getSize()*zoomed));
		target.setFont(nfont);
		
		target.setBackground(bgC); target.setColor(showC);
		target.clearRect(0, 0, getWidth(), getHeight());
		// Preparations ended here!
		particleMutex.lock();
		for(AnyParticleInstance cpi : allParticles) { cpi.drawBall(target, this); }
		target.setColor(showC);
		for(AnyParticleInstance cpi : allParticles) { cpi.drawPosition(target, this); }
		particleMutex.unlock();
		GUImutex.lock();
		for(int i=allGUI.size();i!=0;i--) { allGUI.get(i-1).lbPaint(target); }
		GUImutex.unlock();
		
		target.setColor(cursorC);
		target.fillRect(lbWidth()/2 -3, lbHeight()/2 -3, 5, 5);
	}

	public void lbInitialize() {
		ParticleEngineGUI.owner = this;
		frate = 1000.f / fdelay; oneOverFrate = fdelay / 1000.f;
		ptimer.scheduleAtFixedRate(ptask, 0, fdelay);
		utimer.scheduleAtFixedRate(utask, 0, fdelay);
		dtimer.scheduleAtFixedRate(dtask, 0, fdelay);
		super.addMouseListener(Mhandle);
		super.addMouseWheelListener(Mhandle);
		owner.addKeyListener(this);
	}
	@Override
	public void paintComponent(Graphics graphics) {
		owner.lbPaint((Graphics2D) graphics);
	}
	public static abstract class Aplication extends JFrame {
		private static final long serialVersionUID = -6772296349909919396L;
		public boolean isSpaceRead = false, isBackSpaceRead = false, isMIopen = false, isMIlisteningCommand = false;
		
		public ParticleEngine_v1 mengine;
		
		public Aplication() {
			mengine = new ParticleEngine_v1(this);
			super.setDefaultCloseOperation(EXIT_ON_CLOSE);
			super.pack();
			super.setLocationRelativeTo(null);
			super.setVisible(true);
		}

		public abstract void lbPaint(Graphics2D target);
		public abstract void lbUpdate();
	}
	public ParticleEngine_v1(Aplication owner) {
		this.owner = owner;
		kbhandle = new lbKeyBoardHandle(); Mhandle = new lbMouseHandle();
		super.setPreferredSize(new Dimension(800, 600));
		super.setDoubleBuffered(true);
		owner.add(this);
	}
	
	
	public static class TestAplication extends Aplication {
		private static final long serialVersionUID = -1164833887701852264L;
		ParticleEngine_v1 mengine;
		
		public TestAplication() {
			mengine = new ParticleEngine_v1(this);
			super.setDefaultCloseOperation(EXIT_ON_CLOSE);
			super.pack();
			super.setLocationRelativeTo(null);
			super.setVisible(true);
		}
		
		@Override
		public void lbPaint(Graphics2D target) {
			mengine.lbPaint(target);
			// GUI should start here:
			target.setColor(new Color(11, 7, 31));
			target.fillRect(mengine.lbWidth(), 0, mengine.getWidth()-mengine.lbWidth(), mengine.lbHeight());
		}
		@Override
		public void lbUpdate() {
			mengine.lbUpdate();
		}
	}
	public static void main(String args[]) {
		TestAplication aplication = new TestAplication();
		aplication.mengine.lbInitialize();
	}
	
}
