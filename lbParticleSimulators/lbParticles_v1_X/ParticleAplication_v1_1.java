package lbParticles_v1_X;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.FieldedFileSaver;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.FileHandle;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.FileLoader;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.BasicParticleTypeGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.FieldAudienceGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.HorrizontalRepellingBorderLineGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.NumberVariableGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.PlainFieldGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.PointVariableGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.AnyFileHandle.GUIpointer.VerticalRepellingBorderLineGUIP;
import lbParticles_v1_X.ParticleAplication_v1_1.DataUtility.lbFile;
import lbParticles_v1_X.ParticleAplication_v1_1.GUIselection.FatherGUIselection;
import lbParticles_v1_X.ParticleEngine_v1.AnyAudience.FieldAudience;
import lbParticles_v1_X.ParticleEngine_v1.AnyBorderLine.HorrizontalRepellingBorderLine;
import lbParticles_v1_X.ParticleEngine_v1.AnyBorderLine.VerticalRepellingBorderLine;
import lbParticles_v1_X.ParticleEngine_v1.AnyField;
import lbParticles_v1_X.ParticleEngine_v1.AnyField.AnyFieldTargettedOne;
import lbParticles_v1_X.ParticleEngine_v1.AnyField.PlainField;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleInstance;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleInstance.BasicParticleInstance;
import lbParticles_v1_X.ParticleEngine_v1.AnyParticleType.BasicParticleType;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.AnyVariableListener;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.NumberVariable;
import lbParticles_v1_X.ParticleEngine_v1.AnyVariable.PointVariable;
import lbParticles_v1_X.ParticleEngine_v1.Aplication;
import lbParticles_v1_X.ParticleEngine_v1.Engine.point2f;
import lbParticles_v1_X.ParticleEngine_v1.Engine.point2i;
import lbParticles_v1_X.ParticleEngine_v1.ParticleEngineGUI;
import lbParticles_v1_X.ParticleEngine_v1.lbGUIelement;
import lbParticles_v1_X.ParticleEngine_v1.lbGUIelement.lbButton;

public class ParticleAplication_v1_1 extends Aplication {
	private static final long serialVersionUID = -8766901153542658182L;

	public static void log(String message) { System.out.println(message); }
	
	public static int indexOf(Object[] arr, Object object) {
		for(int i=0;i!=arr.length;i++) { if(arr[i] == object) { return i; } }
		return -1;
	}
	

	public class TypeConverting {
		public static byte[] short2bytes(short value) { return new byte[] { (byte) (value >> 8), (byte) (value) }; }
		public static short bytes2short(byte[] bytes) { return (short) ((bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF)); }
		public static byte[] int2bytes(int value) {
		    return new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) (value) };
		}
		public static int bytes2int(byte[] bytes) {
			return bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
		}
		public static byte[] long2bytes(long value) {
		    return new byte[] {
		    		(byte) (value >> 56), (byte) (value >> 48), (byte) (value >> 40), (byte) (value >> 32),
		    		(byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) (value) };
		}
		public static long bytes2long(byte[] bytes) {
			long lb = ((long)bytes[0]) << 56 | (((long)bytes[1]) & 0xFF) << 48 | (((long)bytes[2]) & 0xFF) << 40;
			lb += (((long)bytes[3]) & 0xFF) << 32 | (((long)bytes[4]) & 0xFF) << 24 | (((long)bytes[5]) & 0xFF) << 16;
			lb += (((long)bytes[3]) & 0xFF) << 8 | (((long)bytes[4]) & 0xFF);
			return lb;
		}
		public static byte[] float2bytes(float value) {
			int d = Float.floatToIntBits(value);
		    return new byte[] { (byte) (d >> 24), (byte) (d >> 16), (byte) (d >> 8), (byte) (d) };
		}
		public static float bytes2float(byte[] bytes) {
			int intBits = bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
			return Float.intBitsToFloat(intBits);
		}
		public static byte[] double2bytes(double value) {
			long d = Double.doubleToLongBits(value);
		    return new byte[] {
		    		(byte) (d >> 56), (byte) (d >> 48), (byte) (d >> 40), (byte) (d >> 32),
		    		(byte) (d >> 24), (byte) (d >> 16), (byte) (d >> 8), (byte) (d) };
		}
		public static double bytes2Double(byte[] bytes) {
			long lb = ((long)bytes[0]) << 56 | (((long)bytes[1]) & 0xFF) << 48 | (((long)bytes[2]) & 0xFF) << 40;
			lb += (((long)bytes[3]) & 0xFF) << 32 | (((long)bytes[4]) & 0xFF) << 24 | (((long)bytes[5]) & 0xFF) << 16;
			lb += (((long)bytes[3]) & 0xFF) << 8 | (((long)bytes[4]) & 0xFF);
			return Double.longBitsToDouble(lb);
		}
		
		public static byte[] char2byteArray(char[] chars) {
			byte[] result = new byte[chars.length*2];
			for(int i=0;i!=chars.length;i++) { int c = chars[i]; boolean isneg = false;
				if(c<0) { isneg = true; c*=-1; }
				result[i*2] = (byte) ((c>>8)%(1<<8)); result[i*2+1] = (byte) ((c>>0)%(1<<8));
				if(isneg) { result[i*2] += 128; }
			} return result;
		}
		public static char[] byte2charArray(byte[] bytes) {
			char[] result = new char[bytes.length/2];
			for(int i=0;i!=result.length;i++) {
				int h = bytes[i*2], l = bytes[i*2+1]; boolean isneg = false;
				if(h>127) { isneg = true; h -= 128; }
				if(h<0) { h=(1<<8)+h; } if(l<0) { l=(1<<8)+l; }
				int res = ((h<<8) + (l<<0)); if(isneg) { res*=-1; } result[i] = (char) res;
			} return result;
		}

	}
	
	public static class DataUtility {
		/*    TO DO:
		 * make interfaces called hasBuffered, hasBufferer
		 * and make lbDataObject inherith lbBuffered and lbBufferer
		 * make a interface called storable
		 */
		public static interface lbSender { //todo: handle the add_Ship method
			//todo: also start to use add_ methods in send methods
			DataOutputStream getOuth();
			public default lbSender send(lbDataShip ship) throws IOException { ship.send_Ship(this); getOuth().flush(); return this; }
			public default lbSender send(byte data) throws IOException { getOuth().write(data); getOuth().flush(); return this; }
			public default lbSender sendBoolean(boolean data) throws IOException { addBoolean(data); getOuth().flush(); return this; }
			public default lbSender sends(byte[] data) throws IOException {
				getOuth().writeInt(data.length); getOuth().write(data); getOuth().flush(); return this;
			}
			public default lbSender sends(byte[] data, int offset, int length) throws IOException {
				getOuth().write(data, offset, length); getOuth().flush(); return this;
			}
			public default lbSender sendChars(char[] string) throws IOException {
				sends(TypeConverting.char2byteArray(string)); return this;
			}
			public default lbSender sendShort(short data) throws IOException { getOuth().writeShort(data); getOuth().flush(); return this; }
			public default lbSender sendInt(int data) throws IOException { getOuth().writeInt(data); getOuth().flush(); return this; }
			public default lbSender sendLong(long data) throws IOException { getOuth().writeLong(data); getOuth().flush(); return this; }
			public default lbSender sendFloat(float data) throws IOException { getOuth().writeFloat(data); getOuth().flush(); return this; }
			public default lbSender sendDouble(double data) throws IOException { getOuth().writeDouble(data); getOuth().flush(); return this; }

			public default lbSender flush() throws IOException { getOuth().flush(); return this; }
			public default lbSender add(lbDataShip ship) throws IOException { ship.send_Ship(this); return this; }
			public default lbSender add(byte data) throws IOException { getOuth().write(data); return this; }
			public default lbSender addBoolean(boolean data) throws IOException { getOuth().write(data ? 1:0); return this; }
			public default lbSender adds(byte[] data) throws IOException {
				getOuth().writeInt(data.length); getOuth().write(data); return this;
			}
			public default lbSender adds(byte[] data, int offset, int length) throws IOException {
				getOuth().write(data, offset, length); return this;
			}
			public default lbSender addChars(char[] string) throws IOException {
				sends(TypeConverting.char2byteArray(string)); return this;
			}
			public default lbSender addShort(short data) throws IOException { getOuth().writeShort(data); return this; }
			public default lbSender addInt(int data) throws IOException { getOuth().writeInt(data); return this; }
			public default lbSender addLong(long data) throws IOException { getOuth().writeLong(data); return this; }
			public default lbSender addFloat(float data) throws IOException { getOuth().writeFloat(data); return this; }
			public default lbSender addDouble(double data) throws IOException { getOuth().writeDouble(data); return this; }
		
		}
		public static interface lbLoader {
			DataInputStream getInph();
			public default lbLoader load(lbDataShip ship) throws IOException { ship.load_Ship(this); return this; }
			public default lbDataShip load(lbDataShipBuilder builder) throws IOException { return builder.build(this); }
			public default byte load() throws IOException { return (byte) getInph().read(); }
			public default boolean loadBoolean() throws IOException { return getInph().read() != 0; }
			public default byte[] loads() throws IOException {
				byte[] bytes = new byte[getInph().readInt()]; getInph().read(bytes); return bytes;
			}
			public default int loads(byte[] buffer, int offset, int length) throws IOException {
				return getInph().read(buffer, offset, length);
			}
			public default char[] loadChars() throws IOException { return TypeConverting.byte2charArray(loads()); }
			public default short loadShort() throws IOException { return getInph().readShort(); }
			public default int loadInt() throws IOException { return getInph().readInt();  }
			public default long loadLong() throws IOException { return getInph().readLong(); }
			public default float loadFloat() throws IOException { return getInph().readFloat(); }
			public default double loadDouble() throws IOException { return getInph().readDouble(); }
		}
		public static abstract class lbDataHarbor implements lbLoader, lbSender {
			public DataOutputStream outh; public DataInputStream inph;
			@Override public DataOutputStream getOuth() { return outh; }
			@Override public DataInputStream getInph() { return inph; }
			public void lbClose() throws IOException {
				if(outh!=null) { outh.close(); outh = null; } if(inph!=null) { inph.close(); inph = null; }
			}
		}
		public static interface lbDataPackage { //todo: make getDataLength method by a way
			public lbDataObject getLBdata(); public lbDataObject setLBdata(lbDataObject data);
		}
		public static interface lbDataShip { //todo: handle the add_Ship method
			public lbDataShip send_Ship(lbSender sender) throws IOException;
			public lbDataShip load_Ship(lbLoader loader) throws IOException;
		}
		public static interface lbDataShipBuilder { //todo: make those buildFrom
			public lbDataShip build(lbLoader loader) throws IOException;
			public lbDataShip build(lbDataObject object) throws IOException;
		}
		public static class lbDataObject implements lbDataShip, lbDataPackage { //todo: make anoughter array copying way
			public byte[] data; public int used, taken=0;
			public void deleteData() { data = new byte[0]; used = 0; taken = 0; }
			public lbDataObject(lbLoader harbor) throws IOException { this.data = harbor.loads(); used = this.data.length; }
			public lbDataObject(byte[] data) {
				this.data = new byte[data.length]; used = data.length;
				for(int i=0;i!=data.length;i++) { this.data[i] = data[i]; }
			}
			public lbDataObject() { data = new byte[0]; used = 0; }
			public ArrayList<lbDataObjectReader> sectionReaders = new ArrayList<lbDataObjectReader>();
			public lbDataObjectReader createReader(int start) { 
				lbDataObjectReader result = new lbDataObjectReader(this);
				result.cursor = start; sectionReaders.add(result); return result;
			}
			public lbDataObjectReader createReader() { return createReader(taken); }
			public void dispose() { for(lbDataObjectReader r:sectionReaders) { r.target = null; } sectionReaders = null; }
			
			public void increasCapacity(int difference) {
				byte[] temp = new byte[data.length+difference];
				for(int i=0;i!=data.length;i++) { temp[i] = data[i]; } data = temp;
			}
			public void setSpace(int length) {
				int request = length - (data.length - used);
				byte[] temp = new byte[data.length+request];
				for(int i=0;i!=data.length;i++) { temp[i] = data[i]; } data = temp;
			}
			public void pushSpace(int minimum) {
				int request = minimum - (data.length - used); if(request<=0) { return; }
				byte[] temp = new byte[data.length+request];
				for(int i=0;i!=data.length;i++) { temp[i] = data[i]; } data = temp;
			}

			public void adds(byte[] value, int requested) {
				int rl = value.length > requested ? requested : value.length;
				pushSpace(rl+4); puts(value, rl); }
			public void adds(byte[] value) { pushSpace(value.length+4); puts(value); }
			public void add(byte value) { pushSpace(1); put(value); }
			public void add(lbDataObject other) { adds(other.data, other.used); }
			public void addShort(short value) { pushSpace(2); byte[] r = TypeConverting.short2bytes(value); put(r[0]); put(r[1]); }
			public void addInt(int value) { pushSpace(4); byte[] r = TypeConverting.int2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); }
			public void addLong(long value) { pushSpace(8); byte[] r = TypeConverting.long2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); put(r[4]); put(r[5]); put(r[6]); put(r[7]); }
			public void addFloat(float value) { pushSpace(4); byte[] r = TypeConverting.float2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); }
			public void addDouble(double value) { pushSpace(8); byte[] r = TypeConverting.double2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); put(r[4]); put(r[5]); put(r[6]); put(r[7]); }
			
			public void puts(byte[] value, int requested)
			{ putInt(value.length); for(int i=0;i!=requested;i++) { data[i+used] = value[i]; } used += requested; }
			public void puts(byte[] value)
			{ putInt(value.length); for(int i=0;i!=value.length;i++) { data[i+used] = value[i]; } used += value.length; }
			public void put(byte value) { data[used] = value; used++; }
			public void putShort(short value) { byte[] r = TypeConverting.short2bytes(value); put(r[0]); put(r[1]); }
			public void putInt(int value) { byte[] r = TypeConverting.int2bytes(value); put(r[0]); put(r[1]); put(r[2]); put(r[3]); }
			public void putLong(long value) { byte[] r = TypeConverting.long2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); put(r[4]); put(r[5]); put(r[6]); put(r[7]); }
			public void putFloat(float value) { byte[] r = TypeConverting.float2bytes(value); put(r[0]); put(r[1]); put(r[2]); put(r[3]); }
			public void putDouble(double value) { byte[] r = TypeConverting.double2bytes(value);
			put(r[0]); put(r[1]); put(r[2]); put(r[3]); put(r[4]); put(r[5]); put(r[6]); put(r[7]); }
			
			public byte[] takes() { byte[] result = new byte[takeInt()];
			for(int i=0;i!=result.length;i++) { result[i] = data[i+taken]; }
			taken += result.length; return result; }
			public byte take() { taken++; return data[taken-1]; }
			public short takeShort() { return TypeConverting.bytes2short(new byte[] { take(), take()}); }
			public int takeInt() { return TypeConverting.bytes2int(new byte[] { take(), take(), take(), take()}); }
			public long takeLong()
			{ return TypeConverting.bytes2long(new byte[] { take(), take(), take(), take(), take(), take(), take(), take()}); }
			public float takeFloat() { return TypeConverting.bytes2float(new byte[] { take(), take(), take(), take()}); }
			public double takeDouble()
			{ return TypeConverting.bytes2Double(new byte[] { take(), take(), take(), take(), take(), take(), take(), take()}); }
			
			public void append(lbDataObject other) { add(other.getLBdata()); }
			
			@Override public lbDataObject getLBdata() { return this; }
			@Override public lbDataObject setLBdata(lbDataObject data) {
				this.data = new byte[data.data.length]; used = data.used; taken = data.taken;
				for(int i=0;i!=used;i++) { this.data[i] = data.data[i]; }
				return this;
			}
			@Override public lbDataObject send_Ship(lbSender harbor) throws IOException { harbor.sends(data); return null; }
			@Override public lbDataObject load_Ship(lbLoader harbor) throws IOException { data = harbor.loads(); return null; }

		}
		public static class lbDataObjectReader {
			public lbDataObject target; public int cursor = 0;
			public lbDataObjectReader(lbDataObject data) { target = data; cursor = data.taken; }

			public byte[] takes() { byte[] result = new byte[takeInt()];
			for(int i=0;i!=result.length;i++) { result[i] = target.data[i+cursor]; }
			cursor += result.length; return result; }
			public byte take() { cursor++; return target.data[cursor-1]; }
			public short takeShort() { return TypeConverting.bytes2short(new byte[] { take(), take()}); }
			public int takeInt() { return TypeConverting.bytes2int(new byte[] { take(), take(), take(), take()}); }
			public long takeLong()
			{ return TypeConverting.bytes2long(new byte[] { take(), take(), take(), take(), take(), take(), take(), take()}); }
			public float takeFloat() { return TypeConverting.bytes2float(new byte[] { take(), take(), take(), take()}); }
			public double takeDouble()
			{ return TypeConverting.bytes2Double(new byte[] { take(), take(), take(), take(), take(), take(), take(), take()}); }
		}
		public static class lbSocket extends lbDataHarbor {
			public Socket self; public lbSocket() { self = null; inph = null; outh = null; }
			public lbSocket(String ip, int port) throws IOException { generate(new Socket(ip, port)); }
			public lbSocket(Socket socket) throws IOException { generate(socket); }
			public void generate(Socket socket) throws IOException {
				self = socket;
				outh = new DataOutputStream(self.getOutputStream());
				inph = new DataInputStream(self.getInputStream());
			}
			public void generate() throws IOException { generate(self); }
			public void close() throws IOException {
				if(self != null) { lbClose(); self.close(); self = null; return; }
				inph = null; outh = null;
			}
			/**
			 * Creates a new socket that connects to the target of current socket.
			 * Current Socket only loads the port of the target. Then everthing is back to normal.
			 * The target lbSocket object must call "createSocket_accept()" before this function.
			 */
			public lbSocket createSocket_connect(int maxTry) {
				String targetAdress = ((InetSocketAddress) self.getRemoteSocketAddress()).getAddress().toString().replace("/","");
				int port = 0; lbSocket connection = null; int tries = 0;
				try { port = loadInt(); } catch (IOException e1) { e1.printStackTrace(); return null; }
				while(maxTry < tries) {
					tries++;
					try { connection = new lbSocket(new Socket(targetAdress, port));
					} catch(IOException e) { e.printStackTrace(); }
				}
				return connection;
			}
			/**
			 * Creates a new socket that connects to the target of current socket.
			 * Current Socket only sends the port of the temporal listener. Then everthing is back to normal.
			 * The target lbSocket object must call "createSocket_connect()" after this function.
			 */
			public lbSocket createSocket_accept() throws IOException {
				ServerSocket listener = new ServerSocket(0);
				sendInt(listener.getLocalPort());
				lbSocket connection = new lbSocket(listener.accept());
				listener.close();
				return connection;
			}
			
			public static interface lbSocketData {
				public lbSocketData send_Socket(lbSocket sender) throws IOException;
				public lbSocketData load_Socket(lbSocket loader) throws IOException;
			}
			public lbSocket sendData(lbSocketData data) throws IOException { data.send_Socket(this); return this; }
			public lbSocket loadData(lbSocketData data) throws IOException { data.load_Socket(this); return this; }
		}
		public static class lbFile extends lbDataHarbor {
			public File self; public lbFile() { self = null; outh = null; inph = null; }
			public lbFile(String path) throws IOException { generate(new File(path)); }
			public lbFile(File file) throws IOException { generate(file); }
			public lbFile(String path, boolean append) throws IOException { generate(new File(path), append); }
			public lbFile(File file, boolean append) throws IOException { generate(file, append); }
			public void generate(File file) throws IOException {
				self = file; self.createNewFile();
				inph = new DataInputStream(new FileInputStream(self));
				outh = new DataOutputStream(new FileOutputStream(self, true));
			}
			public void generate() throws IOException { if(!self.exists()) { self.createNewFile(); } generate(self); }
			public void generate(File file, boolean append) throws IOException {
				self = file; self.createNewFile();
				inph = new DataInputStream(new FileInputStream(self));
				outh = new DataOutputStream(new FileOutputStream(self, append));
			}
			public void generate(boolean append) throws IOException {
				if(!self.exists()) { self.createNewFile(); } generate(self, append);
			}
			public void close() throws IOException { if(self != null) { lbClose(); self = null; return; } inph = null; outh = null; }
			
			public static interface lbFileData {
				public lbFileData send_File(lbFile sender) throws IOException;
				public lbFileData load_File(lbFile loader) throws IOException;
			}
			public lbFile sendData(lbFileData data) throws IOException { data.send_File(this); return this; }
			public lbFile loadData(lbFileData data) throws IOException { data.load_File(this); return this; }
		}
	}
	
	
	public static abstract class AnyFileHandle extends ParticleEngineGUI {
		public FileHandle handle = null;
		
		public static abstract class GUIpointer {
			public ParticleEngineGUI gui; public int index; public String name; public point2f center;
			public GUIpointer(ParticleEngineGUI gui, int index) { this.gui = gui; this.index = index; }
			public GUIpointer() { }
			public boolean isSame(ParticleEngineGUI other) { return gui == other; }
			public boolean isType(Class type) { return type == gui.getClass(); }
			public static GUIpointer getIndexed(ArrayList<GUIpointer> array, int index) {
				for(GUIpointer guip : array) { if(guip.index == index) { return guip; } }
				return null;
			}
			public static GUIpointer getLoad(String typeName) {
				if(new String(NumberVariable.TYPENAME).equals(typeName)) { return new NumberVariableGUIP(); }
				if(new String(PointVariable.TYPENAME).equals(typeName)) { return new PointVariableGUIP(); }
				if(new String(PlainField.TYPENAME).equals(typeName)) { return new PlainFieldGUIP(); }
				if(new String(FieldAudience.TYPENAME).equals(typeName)) { return new FieldAudienceGUIP(); }
				if(new String(HorrizontalRepellingBorderLine.TYPENAME).equals(typeName)) { return new HorrizontalRepellingBorderLineGUIP(); }
				if(new String(VerticalRepellingBorderLine.TYPENAME).equals(typeName)) { return new VerticalRepellingBorderLineGUIP(); }
				if(new String(BasicParticleType.TYPENAME).equals(typeName)) { return new BasicParticleTypeGUIP(); }
				return null;
			}
			private static boolean charArrayEquals(char[] first, char[] second) {
				if(first.length != second.length) { return false; }
				for(int i=0;i!=first.length;i++) { if(first[i]!=second[i]) { return false; } }
				return true;
			}

			public abstract boolean compress(ArrayList<GUIpointer> array, point2f saveCenter);
			public abstract boolean extract(ArrayList<GUIpointer> array, point2f loadCenter);
			public abstract boolean save(lbFile target) throws IOException;
			public abstract boolean load(lbFile source) throws IOException;

			public static class NumberVariableGUIP extends GUIpointer {
				public NumberVariableGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public NumberVariableGUIP() { }
				public float variable;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					NumberVariable number = (NumberVariable) gui;
					center = saveCenter.minus().increase(gui.center);
					variable = number.variable;
					boolean isx = false, isy = false;
					for(GUIpointer guip : array) {
						if(guip.isType(PointVariable.class)) {
							PointVariable point = (PointVariable) guip.gui;
							if(point.x == gui) { isx = true; } if(point.y == gui) { isy = true; }
						}
						if(isx && isy) { return true; }
						if(isx || isy) { break; }
					}
					if(isx) { variable -= saveCenter.x; }
					if(isy) { variable -= saveCenter.y; }
					return false;
				}
				public boolean save(lbFile target) throws IOException {
					target.sendChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index); target.addFloat(variable);
					target.flush();
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt(); variable = source.loadFloat();
					NumberVariable number = new NumberVariable();
					gui = number; number.variable = variable;
					number.name = name;
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					gui.center = center.increase(loadCenter);
					boolean isx = false, isy = false;
					for(GUIpointer guip : array) {
						if(guip.isType(PointVariable.class)) {
							PointVariable point = (PointVariable) guip.gui;
							if(point.x == gui) { isx = true; } if(point.y == gui) { isy = true; }
						}
						if(isx && isy) { return true; }
						if(isx || isy) { break; }
					}
					if(isx) { variable += loadCenter.x; }
					if(isy) { variable += loadCenter.y; }
					return false;
				}
			}
			public static class PointVariableGUIP extends GUIpointer {
				public PointVariableGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public PointVariableGUIP() { }
				public point2f variable;
				public int xindex = -1, yindex = -1;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					PointVariable point = (PointVariable) gui;
					center = saveCenter.minus().increase(gui.center);
					NumberVariableGUIP xp = null, yp = null;
					variable = point.variable;
					boolean xreal = point.x != null, yreal = point.y != null;
					if(xreal) {
						for(GUIpointer px : array) { if(px.isSame(point.x)) { xp = (NumberVariableGUIP) px; break; } }
					}
					if(yreal) {
						for(GUIpointer py : array) { if(py.isSame(point.y)) { yp = (NumberVariableGUIP) py; break; } }
					}
					if(xreal) { xindex = xp.index; } else { xindex = -1; variable.x -= saveCenter.x; }
					if(yreal) { yindex = yp.index; } else { yindex = -1; variable.y -= saveCenter.y; }
					
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index);
					target.addInt(xindex); if(xindex == -1) { target.addFloat(variable.x); }
					target.addInt(yindex); if(yindex == -1) { target.addFloat(variable.y); }
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt();
					xindex = source.loadInt(); float xvalue = 0.f;
					if(xindex == -1) { xvalue = source.loadFloat(); }
					yindex = source.loadInt(); float yvalue = 0.f;
					if(yindex == -1) { yvalue = source.loadFloat(); }
					variable = new point2f(xvalue, yvalue);
					PointVariable point = new PointVariable();
					gui = point; gui.name = name;
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					PointVariable point = (PointVariable) gui;
					point.variable = variable;
					gui.center = center.increase(loadCenter);
					if(xindex != -1) { point.x = (NumberVariable) getIndexed(array, xindex).gui; point.x.addListener(point); }
					if(yindex != -1) { point.y = (NumberVariable) getIndexed(array, yindex).gui; point.y.addListener(point); }
					return false;
				}
			}
			public static class PlainFieldGUIP extends GUIpointer {
				public PlainFieldGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public PlainFieldGUIP() { }
				public int xindex = -1, yindex = -1, FCindex = -1;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					PlainField field = (PlainField) gui;
					center = saveCenter.minus().increase(gui.center);
					NumberVariableGUIP xp = null, yp = null; PointVariableGUIP fcp = null;
					boolean xreal = field.xExtend != null, yreal = field.yExtend != null, FCreal = field.fieldCenter != null ;
					if(xreal) {
						for(GUIpointer px : array) { if(px.isSame(field.xExtend)) { xp = (NumberVariableGUIP) px; break; } }
					}
					if(yreal) {
						for(GUIpointer py : array) { if(py.isSame(field.yExtend)) { yp = (NumberVariableGUIP) py; break; } }
					}
					if(FCreal) {
						for(GUIpointer pfc : array) { if(pfc.isSame(field.fieldCenter)) { fcp = (PointVariableGUIP) pfc; break; } }
					}
					if(xreal) { xindex = xp.index; } else { xindex = -1; }
					if(yreal) { yindex = yp.index; } else { yindex = -1; }
					if(FCreal) { FCindex = fcp.index; } else { FCindex = -1; }
					
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index);
					target.addInt(xindex); target.addInt(yindex); target.addInt(FCindex);
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt();
					xindex = source.loadInt(); yindex = source.loadInt(); FCindex = source.loadInt();
					PlainField field = new PlainField();
					gui = field; gui.name = name;
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					PlainField field = (PlainField) gui;
					gui.center = center.increase(loadCenter);
					if(xindex != -1) { field.xExtend = (NumberVariable) getIndexed(array, xindex).gui; }
					if(yindex != -1) { field.yExtend = (NumberVariable) getIndexed(array, yindex).gui; }
					if(FCindex != -1) { field.fieldCenter = (PointVariable) getIndexed(array, FCindex).gui; }
					return false;
				}
			}
			public static class FieldAudienceGUIP extends GUIpointer {
				public FieldAudienceGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public FieldAudienceGUIP() { }
				public int findex = -1;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					FieldAudience faudience = (FieldAudience) gui;
					center = saveCenter.minus().increase(gui.center);
					boolean freal = faudience.targetField != null;
					if(freal) {
						for(GUIpointer pf : array) { if(pf.isSame(faudience.targetField)) { findex = pf.index; break; } }
					}
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index); target.addInt(findex);
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt(); findex = source.loadInt();
					FieldAudience faudience = new FieldAudience();
					gui = faudience; gui.name = name;
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					FieldAudience faudience = (FieldAudience) gui;
					gui.center = center.increase(loadCenter);
					if(findex != -1) { faudience.targetField = (AnyField) getIndexed(array, findex).gui; }
					return false;
				}
			}
			public static class HorrizontalRepellingBorderLineGUIP extends GUIpointer {
				public HorrizontalRepellingBorderLineGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public HorrizontalRepellingBorderLineGUIP() { }
				public int Cindex = -1, Eindex = -1, Windex = -1, Findex = -1;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					HorrizontalRepellingBorderLine hrbl = (HorrizontalRepellingBorderLine) gui;
					center = saveCenter.minus().increase(gui.center);
					boolean Creal = hrbl.borderCenter != null, Ereal = hrbl.extend != null,
							Wreal = hrbl.width != null, Freal = hrbl.force != null;
					if(Creal) {
						for(GUIpointer p : array) { if(p.isSame(hrbl.borderCenter)) { Cindex = p.index; break; } }
					}
					if(Ereal) {
						for(GUIpointer p : array) { if(p.isSame(hrbl.extend)) { Eindex = p.index; break; } }
					}
					if(Wreal) {
						for(GUIpointer p : array) { if(p.isSame(hrbl.width)) { Windex = p.index; break; } }
					}
					if(Freal) {
						for(GUIpointer p : array) { if(p.isSame(hrbl.force)) { Findex = p.index; break; } }
					}
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					HorrizontalRepellingBorderLine hrbl = (HorrizontalRepellingBorderLine) gui;
					gui.center = center.increase(loadCenter);
					if(Cindex != -1) { hrbl.borderCenter = (PointVariable) getIndexed(array, Cindex).gui; }
					if(Eindex != -1) { hrbl.extend = (NumberVariable) getIndexed(array, Eindex).gui; }
					if(Windex != -1) { hrbl.width = (NumberVariable) getIndexed(array, Windex).gui; }
					if(Findex != -1) { hrbl.force = (NumberVariable) getIndexed(array, Findex).gui; }
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index);
					target.addInt(Cindex); target.addInt(Eindex);
					target.addInt(Windex); target.addInt(Findex);
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt();
					Cindex = source.loadInt(); Eindex = source.loadInt();
					Windex = source.loadInt(); Findex = source.loadInt();
					HorrizontalRepellingBorderLine hrbl = new HorrizontalRepellingBorderLine();
					gui = hrbl; gui.name = name;
					return false;
				}				
			}
			public static class VerticalRepellingBorderLineGUIP extends GUIpointer {
				public VerticalRepellingBorderLineGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public VerticalRepellingBorderLineGUIP() { }
				public int Cindex = -1, Eindex = -1, Windex = -1, Findex = -1;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					VerticalRepellingBorderLine vrbl = (VerticalRepellingBorderLine) gui;
					center = saveCenter.minus().increase(gui.center);
					boolean Creal = vrbl.borderCenter != null, Ereal = vrbl.extend != null,
							Wreal = vrbl.width != null, Freal = vrbl.force != null;
					if(Creal) {
						for(GUIpointer p : array) { if(p.isSame(vrbl.borderCenter)) { Cindex = p.index; break; } }
					}
					if(Ereal) {
						for(GUIpointer p : array) { if(p.isSame(vrbl.extend)) { Eindex = p.index; break; } }
					}
					if(Wreal) {
						for(GUIpointer p : array) { if(p.isSame(vrbl.width)) { Windex = p.index; break; } }
					}
					if(Freal) {
						for(GUIpointer p : array) { if(p.isSame(vrbl.force)) { Findex = p.index; break; } }
					}
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					VerticalRepellingBorderLine vrbl = (VerticalRepellingBorderLine) gui;
					gui.center = center.increase(loadCenter);
					if(Cindex != -1) { vrbl.borderCenter = (PointVariable) getIndexed(array, Cindex).gui; }
					if(Eindex != -1) { vrbl.extend = (NumberVariable) getIndexed(array, Eindex).gui; }
					if(Windex != -1) { vrbl.width = (NumberVariable) getIndexed(array, Windex).gui; }
					if(Findex != -1) { vrbl.force = (NumberVariable) getIndexed(array, Findex).gui; }
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index);
					target.addInt(Cindex); target.addInt(Eindex);
					target.addInt(Windex); target.addInt(Findex);
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt();
					Cindex = source.loadInt(); Eindex = source.loadInt();
					Windex = source.loadInt(); Findex = source.loadInt();
					VerticalRepellingBorderLine vrbl = new VerticalRepellingBorderLine();
					gui = vrbl; gui.name = name;
					return false;
				}				
			}
			public static class BasicParticleTypeGUIP extends GUIpointer {
				public BasicParticleTypeGUIP(ParticleEngineGUI gui, int index) { super(gui, index); }
				public BasicParticleTypeGUIP() { }
				public int SPindex = -1, SCindex = -1, Rindex = -1, Mindex = -1, Tindex = -1;
				public boolean isMoving, isXmoving, isYmoving, isCollidingOT, isCollidingST;
				public ArrayList<AnyParticleInstance> particles;
				@Override
				public boolean compress(ArrayList<GUIpointer> array, point2f saveCenter) {
					BasicParticleType basicPT = (BasicParticleType) gui;
					center = saveCenter.minus().increase(gui.center);
					boolean SPreal = basicPT.spawnPosition != null, SCreal = basicPT.spawnCountV != null,
							Rreal = basicPT.radiusV != null, Mreal = basicPT.massV != null,
							Treal = basicPT.toughnessV != null;
					if(SPreal) {
						for(GUIpointer p : array) { if(p.isSame(basicPT.spawnPosition)) { SPindex = p.index; break; } }
					}
					if(SCreal) {
						for(GUIpointer p : array) { if(p.isSame(basicPT.spawnCountV)) { SCindex = p.index; break; } }
					}
					if(Rreal) {
						for(GUIpointer p : array) { if(p.isSame(basicPT.radiusV)) { Rindex = p.index; break; } }
					}
					if(Mreal) {
						for(GUIpointer p : array) { if(p.isSame(basicPT.massV)) { Mindex = p.index; break; } }
					}
					if(Treal) {
						for(GUIpointer p : array) { if(p.isSame(basicPT.toughnessV)) { Tindex = p.index; break; } }
					}
					isMoving = basicPT.isMoving(); isXmoving = basicPT.isXmoving(); isYmoving = basicPT.isYmoving();
					isCollidingOT = basicPT.isCollidingOT(); isCollidingST = basicPT.isCollidingST();
					particles = basicPT.instances;
					return false;
				}
				@Override
				public boolean extract(ArrayList<GUIpointer> array, point2f loadCenter) {
					BasicParticleType basicPT = (BasicParticleType) gui;
					gui.center = center.increase(loadCenter);
					if(SPindex != -1) { basicPT.spawnPosition = (PointVariable) getIndexed(array, SPindex).gui; }
					if(SCindex != -1) { basicPT.spawnCountV = (NumberVariable) getIndexed(array, SCindex).gui; }
					if(Rindex != -1) { basicPT.radiusV = (NumberVariable) getIndexed(array, Rindex).gui; }
					if(Mindex != -1) { basicPT.massV = (NumberVariable) getIndexed(array, Mindex).gui; }
					if(Tindex != -1) { basicPT.toughnessV = (NumberVariable) getIndexed(array, Tindex).gui; }
					basicPT.collidingOTb.istrue = isCollidingOT; basicPT.collidingSTb.istrue = isCollidingST;
					basicPT.movingB.istrue = isMoving;
					basicPT.xmovingB.istrue = isXmoving; basicPT.ymovingB.istrue = isYmoving;
					owner.particleMutex.dlock("BasicParticleTypeGUIP \"extract\" Function");
					basicPT.pending = particles;
					owner.particleMutex.dunlock("BasicParticleTypeGUIP \"extract\" Function");
					return false;
				}
				@Override
				public boolean save(lbFile target) throws IOException {
					target.addChars(gui.getTypeName());
					target.addChars(gui.name.toCharArray());
					target.addFloat(center.x); target.addFloat(center.y);
					target.addInt(index); target.addInt(SPindex); target.addInt(SCindex);
					target.addInt(Rindex); target.addInt(Mindex); target.addInt(Tindex);
					target.addBoolean(isMoving); target.addBoolean(isXmoving); target.addBoolean(isYmoving);
					target.addBoolean(isCollidingOT); target.addBoolean(isCollidingST);
					owner.particleMutex.dlock("BasicParticleTypeGUIP \"save\" Function");
					int Pcount = particles.size();
					target.addInt(Pcount); target.flush();
					ArrayList<AnyParticleInstance> instances = ((BasicParticleType)gui).instances;
					for(int i=0;i!=Pcount;i++) {
						AnyParticleInstance.BasicParticleInstance instance = (BasicParticleInstance) instances.get(i);
						target.addFloat(instance.position.x); target.addFloat(instance.position.y);
						target.addFloat(instance.vel.x); target.addFloat(instance.vel.y);
						if(i % 128 == 0) { target.flush(); }
					}
					owner.particleMutex.dunlock("BasicParticleTypeGUIP \"save\" Function");
					target.flush();
					return false;
				}
				@Override
				public boolean load(lbFile source) throws IOException {
					name = new String(source.loadChars());
					center = new point2f(source.loadFloat(), source.loadFloat());
					index = source.loadInt(); SPindex = source.loadInt(); SCindex = source.loadInt();
					Rindex = source.loadInt(); Mindex = source.loadInt(); Tindex = source.loadInt();
					isMoving = source.loadBoolean(); isXmoving = source.loadBoolean(); isYmoving = source.loadBoolean();
					isCollidingOT = source.loadBoolean(); isCollidingST = source.loadBoolean();
					BasicParticleType basicPT = new BasicParticleType();
					gui = basicPT; gui.name = name;
					int Pcount = source.loadInt();
					particles = new ArrayList<AnyParticleInstance>();
					particles.ensureCapacity(Pcount);
					for(int i=0;i!=Pcount;i++) {
						AnyParticleInstance.BasicParticleInstance instance = new AnyParticleInstance.BasicParticleInstance(basicPT);
						instance.position.x = source.loadFloat(); instance.position.y = source.loadFloat();
						instance.vel.x = source.loadFloat(); instance.vel.y = source.loadFloat();
						particles.set(i, instance);
					}
					return false;
				}
			}

		}

		public static class FileHandle extends ParticleEngineGUI {
			// 6 rows, spaced 10 pixels
			public static final point2f rsize = new point2f();
			public static final char[] TYPENAME = "File Handle: FILE".toCharArray();
			@Override public char[] getTypeName() { return TYPENAME; }

			public static final point2f typeStart    = new point2f(5               ,   8  + 10*3 + 20*2);
			public static final point2f deleteCenter = new point2f(60/2 +10*1 + 00 , 20/2 + 10*2 + 20*1);
			public static final point2f sCenter      = new point2f(40/2 +10*2 + 60 , 20/2 + 10*2 + 20*1);
			public static final point2f moveCenter   = new point2f(50/2 +10*3 +100 , 20/2 + 10*2 + 20*1);
			public static final point2f pickCenter   = new point2f(40/2 +10*4 +150 , 20/2 + 10*2 + 20*1);
			public static final point2f valueStart   = new point2f(10              ,   8  + 10);
			
			public lbButton set, pick;
			public boolean iss = false, islistening = false, ismove = false, isrename = false;
			public char[] filePath = new char[0];
			public AnyFileHandle handler;
			
			public lbFile getFile() throws IOException {
				if(filePath == null) { return null; }
				if(filePath.length < 1) { return null; }
				if(filePath[0] == '/') {
					char[] result = new char[filePath.length-1];
					for(int i=0;i!=result.length;i++) { result[i] = filePath[i+1]; }
					return new lbFile(System.getProperty("user.dir") + "/" + new String(result));
				} return new lbFile(new String(filePath));
			}

			public FileHandle() {
				name = "unspecified file"; size = new point2f(10*5 + 60+40+50+40, 10*4 + 20*3);
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				set = new lbButton(this, sCenter, lbGUIelement.rtetralsize, "set");
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				pick = new lbButton(this, pickCenter, lbGUIelement.rtetralsize, "pick");
			}
			@Override public void deleteSelf() { handler.handle = null; owner.removedGUI.add(this); }

			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					delete.lbUpdate(owner, start); set.lbUpdate(owner, start);
					move.lbUpdate(owner, start); pick.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				delete.handleHower(owner, start); set.handleHower(owner, start);
				move.handleHower(owner, start); pick.handleHower(owner, start);
				
				if(owner.kbhandle.escape && !owner.escaped) {
					owner.escaped = true;
					owner.islistening = false;
					owner.isCommandListening = false;
					islistening = false; iss = false;
					ismove = false; isrename = false;
					return;
				}
				if(owner.kbhandle.enter && !owner.entered) {
					owner.entered = true;
					if(iss) {
						owner.command = new char[0]; owner.isCommandListening = false;
						islistening = false; iss = false; name = new String(filePath);
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
				if(isrename) { return; }
				
				if(iss) { filePath = owner.command; name = new String(filePath); }
				
				if(ismove) { handleMove(); return; }
			}
			@Override
			public void lbPaint(Graphics2D target) {
				target.setColor(owner.guiC);
				rsize.copy(size.multiplie(owner.zoomed));
				point2i dstart = owner.mapW2D(center).increase(rsize.multiplie(-0.5f)).toInt();
				point2f wstart = size.multiplie(-0.5f).increase(center);
				target.fillRect(dstart.x, dstart.y, (int)rsize.x, (int)rsize.y);
				delete.lbPaint(target, owner, wstart); set.lbPaint(target, owner, wstart);
				move.lbPaint(target, owner, wstart); pick.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i vpos = owner.mapW2D(wstart.sumwith(valueStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				target.drawChars(filePath, 0, filePath.length, vpos.x, vpos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
			}
			
			@Override public void onlbGUIaction(lbGUIaction action) {
				if(owner.islistening) { return; }
				if(owner.toFront == null) { owner.toFront = this; }
				if(action.reason == pick) { owner.chosenFile = this; return; }
				if(action.reason == delete) { deleteSelf(); return; }
				islistening = true;
				if(action.reason == set) { owner.isCommandListening = true; iss = true; return; }
				if(action.reason == rename) { owner.isCommandListening = true; isrename = true; return; }
				if(action.reason == move) { owner.islistening = true; ismove = true; return; }
			}

		}

		public static class FieldedFileSaver extends AnyFileHandle implements AnyFieldTargettedOne, AnyVariableListener {

			public static final point2f rsize = new point2f(200, 10*9 + 20*8);
			public static final char[] TYPENAME = "File Handle: FIELDED SAVER".toCharArray();

			public static final point2f typeStart    = new point2f(10               ,   8  + 10*8 + 20*7);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*7 + 20*6);
			public static final point2f moveCenter   = new point2f(50/2 +  5*2 +  0 , 20/2 + 10*6 + 20*5);
			public static final point2f renameCenter = new point2f(60/2 +  5*3 + 50 , 20/2 + 10*6 + 20*5);
			public static final point2f deleteCenter = new point2f(60/2 +  5*4 +110 , 20/2 + 10*6 + 20*5);
			public static final point2f centerStart  = new point2f(       10        ,  18  + 10*5 + 20*4);
			public static final point2f xextendStart = new point2f(       10        ,  18  + 10*4 + 20*3);
			public static final point2f yextendStart = new point2f(       10        ,  18  + 10*3 + 20*2);
			public static final point2f saveBCenter  = new point2f(40/2 + 10*1 + 00 , 20/2 + 10*2 + 20*1);
			public static final point2f putSCCenter  = new point2f(110/2+ 10*2 + 40 , 20/2 + 10*2 + 20*1);
			public static final point2f putFCenter   = new point2f(80/2 + 10   +  5 , 20/2 + 10*1 + 20*0);
			public static final point2f putFDCenter  = new point2f(80/2 + 10*2 + 85 , 20/2 + 10*1 + 20*0);


			public lbButton putField, putFile, putSaveCenter, saveB;
			public boolean  islistening = false, ismove = false, isrename = false;

			public PointVariable saveCenter = null;
			public AnyField targetField = null;
			
			@Override public char[] getTypeName() { return TYPENAME; }
			
			public FieldedFileSaver() { super();
				name = "unnamed Plain Field"; size = new point2f(200, 10*9 + 20*8);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				saveB = new lbButton(this, saveBCenter, lbGUIelement.rtetralsize, "Save");
				putSaveCenter = new lbButton(this, putSCCenter, lbGUIelement.rdecamonansize, "put Save Center");
				putField = new lbButton(this, putFDCenter, lbGUIelement.roctalsize, "put Field");
				putFile = new lbButton(this, putFCenter, lbGUIelement.roctalsize, "put File");
			}
			@Override
			public void deleteSelf() {
				if(targetField != null) { targetField.removeFieldTargettedOne(this); }
				if(handle != null) { handle.handler = null; }
				if(saveCenter != null) { saveCenter.removeListener(new VariableRemovalMessage(this, saveCenter, 3)); }
				owner.removedGUI.add(this);
			}
			
			public void save() throws IOException {
				if(handle == null) { return; }
				if(targetField == null) { return; }
				if(saveCenter == null) { return; }
				lbFile target = handle.getFile();
				if(target == null) { return; }
				ArrayList<GUIpointer> guis = new ArrayList<GUIpointer>();
				target.add((byte) 0);
				for(int i=0;i!=owner.allGUI.size();i++) {
					ParticleEngineGUI gui = owner.allGUI.get(i);
					if(!targetField.isInside(gui.center)) { continue; }
					//log("fetching GUI:"+new String(gui.getTypeName()));
					if(gui.getClass() == NumberVariable.class) { guis.add(new NumberVariableGUIP(gui, i)); continue; }
					if(gui.getClass() == PointVariable.class) { guis.add(new PointVariableGUIP(gui, i)); continue; }
					if(gui.getClass() == PlainField.class) { guis.add(new PlainFieldGUIP(gui, i)); continue; }
					if(gui.getClass() == FieldAudience.class) { guis.add(new FieldAudienceGUIP(gui, i)); continue; }
					if(gui.getClass() == HorrizontalRepellingBorderLine.class) { guis.add(new HorrizontalRepellingBorderLineGUIP(gui, i)); continue; }
					if(gui.getClass() == VerticalRepellingBorderLine.class) { guis.add(new VerticalRepellingBorderLineGUIP(gui, i)); continue; }
					if(gui.getClass() == BasicParticleType.class) { guis.add(new BasicParticleTypeGUIP(gui, i)); continue; }
				}
				for(GUIpointer guip : guis) {
					//log("compressing GUI:"+new String(guip.gui.getTypeName()));
					guip.compress(guis, saveCenter.variable);
				}
				for(GUIpointer guip : guis) {
					//log("saving GUI:"+new String(guip.gui.getTypeName()));
					guip.save(target);
				}
				target.flush();
			}
			
			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); putField.lbUpdate(owner, start);
					putFile.lbUpdate(owner, start); putSaveCenter.lbUpdate(owner, start);
					saveB.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); putField.handleHower(owner, start);
				putFile.handleHower(owner, start); putSaveCenter.handleHower(owner, start);
				saveB.handleHower(owner, start);
				
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
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putFile) {
					if(handle != null) { handle.handler = null; }
					if(owner.chosenFile == null) { return; }
					if(owner.chosenFile.getClass() != FileHandle.class) { return; }
					handle = (FileHandle) owner.chosenFile;
					handle.handler = this;
					return;
				}
				if(action.reason == putField) {
					if(targetField != null) { targetField.removeFieldTargettedOne(this); }
					if(owner.chosenField == null) { return; }
					targetField = owner.chosenField;
					targetField.addFieldTargettedOne(this);
					return;
				}
				if(action.reason == putSaveCenter) {
					if(saveCenter != null) { saveCenter.removeListener(new VariableRemovalMessage(this, saveCenter, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					saveCenter = (PointVariable) owner.chosenVariable;
					saveCenter.addListener(this);
					return;
				}
				if(action.reason == saveB) { try { save(); } catch (IOException e) { e.printStackTrace(); return; } return; }
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
				delete.lbPaint(target, owner, wstart); putField.lbPaint(target, owner, wstart);
				putFile.lbPaint(target, owner, wstart); putSaveCenter.lbPaint(target, owner, wstart);
				saveB.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(saveCenter != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(centerStart)).toInt(); chars = saveCenter.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+saveCenter.variable.x+" y:"+saveCenter.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(targetField != null) {
					point2i xpos = owner.mapW2D(wstart.sumwith(xextendStart)).toInt(); chars = targetField.name.toCharArray();
					target.drawChars(chars, 0, chars.length, xpos.x, xpos.y);
					
				}
				if(handle != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(yextendStart)).toInt(); chars = handle.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					
				}
			}

			@Override public void onVariableUpdate(AnyVariable update) { }
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.index > 0) {
					switch(removal.index) {
					case 3: saveCenter = null; break;
					} return;
				}
				if(removal.variable == saveCenter) { saveCenter = null; }
			}
			@Override
			public void removeField(AnyField field) {
				if(field == targetField) { targetField = null; }
			}

		}
		public static class FileLoader extends AnyFileHandle implements AnyVariableListener {

			public static final point2f rsize = new point2f(200, 10*8 + 20*6);
			public static final char[] TYPENAME = "File Handle: FIELDED SAVER".toCharArray();

			public static final point2f typeStart    = new point2f(10               ,   8  + 10*7 + 20*5);
			public static final point2f nameStart    = new point2f(5                ,   8  + 10*6 + 20*4);
			public static final point2f moveCenter   = new point2f(50/2 +  5*2 +  0 , 20/2 + 10*5 + 20*3);
			public static final point2f renameCenter = new point2f(60/2 +  5*3 + 50 , 20/2 + 10*5 + 20*3);
			public static final point2f deleteCenter = new point2f(60/2 +  5*4 +110 , 20/2 + 10*5 + 20*3);
			public static final point2f centerStart  = new point2f(       10        ,  18  + 10*4 + 20*2);
			public static final point2f fileStart    = new point2f(       10        ,  18  + 10*3 + 20*1);
			public static final point2f loadBCenter  = new point2f(110/2+ 10*1 +  0 , 20/2 + 10*2 + 20*1);
			public static final point2f putSCCenter  = new point2f(110/2+ 10*1 +  0 , 20/2 + 10*1 + 20*0);
			public static final point2f putFCenter   = new point2f(60/2 + 10*2 +110 , 20/2 + 10*1 + 20*0);


			public lbButton putFile, putSaveCenter, loadB;
			public boolean  islistening = false, ismove = false, isrename = false;

			public PointVariable loadCenterV = null;
			
			@Override public char[] getTypeName() { return TYPENAME; }
			
			public FileLoader() { super();
				name = "unnamed Plain Field"; size = new point2f(200, 10*7 + 20*6);
				move = new lbButton(this, moveCenter, lbGUIelement.rpentalsize, "move");
				rename = new lbButton(this, renameCenter, lbGUIelement.rhekzalsize, "rename");
				delete = new lbButton(this, deleteCenter, lbGUIelement.rhekzalsize, "delete");
				loadB = new lbButton(this, loadBCenter, lbGUIelement.rtetralsize, "load");
				putSaveCenter = new lbButton(this, putSCCenter, lbGUIelement.rdecamonansize, "put Load Center");
				putFile = new lbButton(this, putFCenter, lbGUIelement.rhekzalsize, "put File");
			}
			@Override
			public void deleteSelf() {
				if(handle != null) { handle.handler = null; }
				if(loadCenterV != null) { loadCenterV.removeListener(new VariableRemovalMessage(this, loadCenterV, 0)); }
				owner.removedGUI.add(this);
			}
			
			public void load() {
				if(handle == null) { return; } if(loadCenterV == null) { return; }
				lbFile file = null; try { file = handle.getFile();
				} catch (IOException e) { e.printStackTrace(); return; }
				if(file == null) { return; }
				log("file ready");
				ArrayList<GUIpointer> guis = new ArrayList<GUIpointer>();
				boolean readable = true;
				try {
					if(file.load() > 0) { return; }
					while(file.inph.available() != 0) {
						log("input aviable!");
						String tname = new String(file.loadChars());
						log("type name is: "+tname);
						GUIpointer gui = GUIpointer.getLoad(tname);
						if(gui == null) { readable = false; break; }
						log("loading GUI");
						gui.load(file); guis.add(gui);
					}
					log("is still readable:"+readable);
					if(!readable) { return; }
				} catch (IOException e) { e.printStackTrace(); }
				for(GUIpointer p : guis) { log(p.gui.name+" extract"); p.extract(guis, loadCenterV.variable); }
				log("extracted");
				for(GUIpointer p : guis) { log(p.gui.name+" odd to pendings"); owner.pendingGUI.add(p.gui); }
				log("added to pendings");
			}
			
			@Override
			public void lbUpdate() {
				point2f start = size.multiplie(-0.5f).increase(center);
				if(!islistening) {
					rename.lbUpdate(owner, start); move.lbUpdate(owner, start);
					delete.lbUpdate(owner, start); loadB.lbUpdate(owner, start);
					putFile.lbUpdate(owner, start); putSaveCenter.lbUpdate(owner, start);
					handleGrab();
					return;
				}
				rename.handleHower(owner, start); move.handleHower(owner, start);
				delete.handleHower(owner, start); loadB.handleHower(owner, start);
				putFile.handleHower(owner, start); putSaveCenter.handleHower(owner, start);
				
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
				if(action.reason == delete) { deleteSelf(); return; }
				if(action.reason == putFile) {
					if(handle != null) { handle.handler = null; }
					if(owner.chosenFile == null) { return; }
					if(owner.chosenFile.getClass() != FileHandle.class) { return; }
					handle = (FileHandle) owner.chosenFile;
					handle.handler = this;
					return;
				}
				if(action.reason == putSaveCenter) {
					if(loadCenterV != null) { loadCenterV.removeListener(new VariableRemovalMessage(this, loadCenterV, 3)); }
					if(owner.chosenVariable == null) { return; }
					if(owner.chosenVariable.getClass() != PointVariable.class) { return; }
					loadCenterV = (PointVariable) owner.chosenVariable;
					loadCenterV.addListener(this);
					return;
				}
				if(action.reason == loadB) { load(); return; }
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
				delete.lbPaint(target, owner, wstart); loadB.lbPaint(target, owner, wstart);
				putFile.lbPaint(target, owner, wstart); putSaveCenter.lbPaint(target, owner, wstart);
				target.setColor(lbGUIelement.text);
				point2i npos = owner.mapW2D(wstart.sumwith(nameStart)).toInt();
				point2i tpos = owner.mapW2D(wstart.sumwith(typeStart)).toInt();
				char[] chars = ("name: "+name).toCharArray();
				target.drawChars(chars, 0, chars.length, npos.x, npos.y);
				target.setColor(lbGUIelement.titleText);
				target.drawChars(TYPENAME, 0, TYPENAME.length, tpos.x, tpos.y);
				if(loadCenterV != null) {
					point2i cpos = owner.mapW2D(wstart.sumwith(centerStart)).toInt(); chars = loadCenterV.name.toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, cpos.y);
					chars = ("x:"+loadCenterV.variable.x+" y:"+loadCenterV.variable.y).toCharArray();
					target.drawChars(chars, 0, chars.length, cpos.x, (int)(cpos.y+15*owner.zoomed));
					
				}
				if(handle != null) {
					point2i ypos = owner.mapW2D(wstart.sumwith(fileStart)).toInt(); chars = handle.name.toCharArray();
					target.drawChars(chars, 0, chars.length, ypos.x, ypos.y);
					
				}
			}

			@Override public void onVariableUpdate(AnyVariable update) { }
			@Override
			public void removeVariable(VariableRemovalMessage removal) {
				if(removal.variable == loadCenterV) { loadCenterV = null; }
			}

		}

	}


	ParticleEngine_v1 mengine;
	public int cursor = 0, cursorMAX = 0, CDY = 20, CDX = 20;
	public static final int dsepY0 = 40, dsepY1 = 30, dsepY2 = 20, DSX = 30;

	public void drawCursor(Graphics2D target) {
		target.fillRect(mengine.lbWidth() +CDX/4, CDY-dsepY0/2, CDX/2, dsepY0);
	}
	
	public static abstract class GUIselection {
		public ParticleAplication_v1_1 owner;
		public FatherGUIselection father;
		char[] name;
		public boolean isSelected() { return owner.chosen == this; }
		
		public GUIselection(ParticleAplication_v1_1 owner, FatherGUIselection father, String name) {
			this.owner = owner; this.father = father; this.name = name.toCharArray();
		}
		public abstract void generate();
		
		public abstract void onLeft(); public abstract void onRight();
		public abstract void lbUpdate(); public abstract void lbPaint(Graphics2D target);
		
		
		public static abstract class FatherGUIselection extends GUIselection {
			public FatherGUIselection(ParticleAplication_v1_1 owner, FatherGUIselection father, String name, int childCount) {
				super(owner, father, name); maxCursor = childCount; childs = new GUIselection[childCount]; generate();
			}
			public GUIselection[] childs; int maxCursor;

			@Override public void onLeft() { }
			@Override public void onRight() { owner.chosen = this; owner.cursor = 0; }
			@Override
			public void lbUpdate() {
				if(owner.mengine.kbhandle.up && !owner.isUread) {
					owner.isUread = true; if(owner.cursor!=0) { owner.cursor--; }
				}
				if(owner.mengine.kbhandle.down && !owner.isDread) {
					owner.isDread = true; if(owner.cursor!=owner.chosen.maxCursor) { owner.cursor++; }
				}
				if(owner.mengine.kbhandle.left && !owner.isLread) {
					owner.isLread = true;
					if(owner.cursor == 0) {
						owner.chosen = father;
						owner.cursor = indexOf(((FatherGUIselection)father).childs, this)+1;
					} else { childs[owner.cursor-1].onLeft(); }
				}
				if(owner.mengine.kbhandle.right && !owner.isRread) {
					owner.isRread = true;
					if(owner.cursor != 0) { childs[owner.cursor-1].onRight(); }
				}
			}
			@Override
			public void lbPaint(Graphics2D target) {
				if(!isSelected()) {
					target.drawChars(name, 0, name.length, owner.mengine.lbWidth() +owner.CDX, owner.CDY);
					return;
				}
				owner.CDY = ParticleAplication_v1_1.dsepY0;
				owner.CDX = ParticleAplication_v1_1.dsepY2;
				if(owner.cursor == 0) { owner.drawCursor(target); }
				target.drawChars(name, 0, name.length, owner.mengine.lbWidth() +owner.CDX, owner.CDY);
				owner.CDY += ParticleAplication_v1_1.dsepY0;
				owner.CDX = ParticleAplication_v1_1.dsepY0;
				for(int i=0;i!=maxCursor;i++) {
					GUIselection child = childs[i];
					owner.CDY += ParticleAplication_v1_1.dsepY0;
					if(owner.cursor == i+1) { owner.drawCursor(target); }
					child.lbPaint(target);
				}
			}
			
		}
		public static abstract class GUIactionSelection extends GUIselection {
			public GUIactionSelection(ParticleAplication_v1_1 owner, FatherGUIselection father, String name) {
				super(owner, father, name); generate();
			}

			@Override public void generate() { }
			@Override public void onLeft() { }
			@Override public void lbUpdate() { }
			@Override
			public void lbPaint(Graphics2D target) {
				target.drawChars(name, 0, name.length, owner.mengine.lbWidth() +owner.CDX, owner.CDY);
			}
			
		}
		public static abstract class GUIstring extends GUIselection {
			public GUIstring(ParticleAplication_v1_1 owner, FatherGUIselection father, String name) {
				super(owner, father, name);
			}
			public char[] result;

			@Override public void generate() { }
			@Override public void onLeft() { }
			@Override public void onRight() { }

			@Override
			public void lbPaint(Graphics2D target) {
				target.drawChars(result, 0, result.length, owner.mengine.lbWidth() +owner.CDX, owner.CDY);
			}
			
		}
		
	}
	public FatherGUIselection chosen = null;

	/* To do later
	 * 
	 * add isactive to borders
	 * add ideal borderlines
	 * add teleporting borderline
	 * add Plain Field borderlines
	 * 
	 * add interaction types
	 * add bound types
	 * add sensor types
	 * add event types
	 * 
	 * add file handle types
	 * * Field Saver
	 * * Loader
	 * 
	 * add a way to save the aplication
	 */
	///
	/* Errors to solve
	 * 
	 * Borderlines speeds up particles!
	 * 
	 */
	
	public void lbInitialize() {
		FatherGUIselection main = new FatherGUIselection(this, null, "Interface Entry", 2) {
			@Override
			public void generate() {
				owner.chosen = this; this.father = this;
				int mainIndex = 0;
				FatherGUIselection statusData = new FatherGUIselection(owner, this, "Status Data", 1) {
					@Override
					public void generate() {
						int statusDataIndex = 0;
						GUIactionSelection paused = new GUIactionSelection(owner, this, "Is Paused: ") {
							char[] result = name;
							@Override
							public void onRight() {
								owner.mengine.ispaused = !owner.mengine.ispaused;
								result = (new String(name) + owner.mengine.ispaused).toCharArray();
							}
							@Override
							public void lbPaint(Graphics2D target) {
								target.drawChars(result, 0, result.length, owner.mengine.lbWidth() +owner.CDX, owner.CDY);
							}
						}; childs[statusDataIndex] = paused; statusDataIndex++;
						//GUIactionSelection
					}
				}; childs[mainIndex] = statusData; mainIndex++;
				FatherGUIselection createElement = new FatherGUIselection(owner, this, "Create Element", 6) {
					@Override
					public void generate() {
						int CEindex = 0;
						FatherGUIselection variable = new FatherGUIselection(owner, this, "Variable", 2) {
							@Override public void generate() {
								int Vindex = 0;
								GUIactionSelection numberV = new GUIactionSelection(owner, this, "Number") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new NumberVariable());
									}
								}; childs[Vindex] = numberV; Vindex++;
								GUIactionSelection pointV = new GUIactionSelection(owner, this, "Point") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new PointVariable());
									}
								}; childs[Vindex] = pointV; Vindex++;
							}
						}; childs[CEindex] = variable; CEindex++;
						FatherGUIselection newField = new FatherGUIselection(owner, this, "Field", 1) {
							@Override public void generate() {
								int Findex = 0;
								GUIactionSelection plainF = new GUIactionSelection(owner, this, "Plain") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new PlainField());
									}
								}; childs[Findex] = plainF; Findex++;
							}
						}; childs[CEindex] = newField; CEindex++;
						FatherGUIselection newAudience = new FatherGUIselection(owner, this, "Audience", 1) {
							@Override public void generate() {
								int Aindex = 0;
								GUIactionSelection fieldedA = new GUIactionSelection(owner, this, "Fielded") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new FieldAudience());
									}
								}; childs[Aindex] = fieldedA; Aindex++;
							}
						}; childs[CEindex] = newAudience; CEindex++;
						FatherGUIselection newBorderline = new FatherGUIselection(owner, this, "Borderline", 2) {
							@Override public void generate() {
								int Bindex = 0;
								GUIactionSelection horrizontalB = new GUIactionSelection(owner, this, "Horrizontal") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new HorrizontalRepellingBorderLine());
									}
								}; childs[Bindex] = horrizontalB; Bindex++;
								GUIactionSelection verticalB = new GUIactionSelection(owner, this, "Vertical") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new VerticalRepellingBorderLine());
									}
								}; childs[Bindex] = verticalB; Bindex++;
							}
						}; childs[CEindex] = newBorderline; CEindex++;
						FatherGUIselection newParticleType = new FatherGUIselection(owner, this, "Particle Type", 1) {
							@Override public void generate() {
								int PTindex = 0;
								GUIactionSelection basicPT = new GUIactionSelection(owner, this, "Basic") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new BasicParticleType());
									}
								}; childs[PTindex] = basicPT; PTindex++;
							}
						}; childs[CEindex] = newParticleType; CEindex++;
						FatherGUIselection newFileHandle = new FatherGUIselection(owner, this, "File Handle", 3) {
							@Override public void generate() {
								int FHindex = 0;
								GUIactionSelection fileH = new GUIactionSelection(owner, this, "File") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new FileHandle());
									}
								}; childs[FHindex] = fileH; FHindex++;
								GUIactionSelection fieldedSaver = new GUIactionSelection(owner, this, "Fielded Saver") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new FieldedFileSaver());
									}
								}; childs[FHindex] = fieldedSaver; FHindex++;
								GUIactionSelection fileLoader = new GUIactionSelection(owner, this, "Loader") {
									@Override public void onRight() {
										mengine.pendingGUI.add(new FileLoader());
									}
								}; childs[FHindex] = fileLoader; FHindex++;
							}
						}; childs[CEindex] = newFileHandle; CEindex++;
					}
				}; childs[mainIndex] = createElement; mainIndex++;
				
			}
		}; chosen = main;
		/*FatherGUIsection main = new FatherGUIsection(this, "Main Interface", 1);
		chosen = main; chosen.father = (FatherGUIsection) main; main.EnterTo();
		FatherGUIsection newElement = new FatherGUIsection(this, "New Element", 5);
		main.addSection(newElement, 0);
		GUIselection newVariable = new GUIselection(this, "Variable", 2);
		newElement.addSection(newVariable, 0);
		
		
		
		GUIselection newParticleType = new GUIselection(this, "Particle Type", 1);
		newElement.addSection(newParticleType, 4);
		newParticleType.desicions[0] = new GUIselection.Desicion(newParticleType, "Simple") {
			@Override public void onDeside() {
				mengine.pendingGUI.add(new AnyParticleType.BasicParticleType(mengine));
			}
		};*/
	}
	public boolean isLread = false, isRread = false, isUread = false, isDread = false;
	@Override
	public void lbUpdate() {
		
		mengine.lbUpdate();
		
		if(!(mengine.islistening || mengine.isCommandListening)) {
			if(mengine.kbhandle.backspace && !isBackSpaceRead) {
				isBackSpaceRead = true;
				if(isMIopen) {
					while(chosen != chosen.father) { chosen = chosen.father; }
					isMIopen = false;
				} else {
					cursor = 0;
					isMIopen = true;
				}
			}
		}
		
		if(!isMIopen) { return; }
		
		chosen.lbUpdate();
		
		if(!mengine.kbhandle.left ) { isLread = false; }
		if(!mengine.kbhandle.right) { isRread = false; }
		if(!mengine.kbhandle.up   ) { isUread = false; }
		if(!mengine.kbhandle.down ) { isDread = false; }
	}
	@Override
	public void lbPaint(Graphics2D target) {
		Font dfont = target.getFont();
		
		mengine.lbPaint(target);
		
		target.setFont(dfont);
		// GUI should start here:
		target.setColor(new Color(11, 7, 31));
		target.fillRect(mengine.lbWidth(), 0, mengine.getWidth()-mengine.lbWidth(), mengine.lbHeight());
		
		if(!isMIopen) { return; }
		CDY = dsepY0;
		target.setColor(new Color(31, 95, 255));
		chosen.lbPaint(target);
	}

	public ParticleAplication_v1_1() {
		lbInitialize();
		mengine = new ParticleEngine_v1(this);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
	public static void main(String[] args) throws IOException {
		ParticleAplication_v1_1 window = new ParticleAplication_v1_1();
		window.mengine.lbInitialize();
		/*
		String str = "leoc oh conteigo";
		lbFile file = new lbFile(System.getProperty("user.dir") + "/deSave0");
		file.sendChars(str.toCharArray());
		file = new lbFile(System.getProperty("user.dir") + "/deSave0");
		String loaded = new String(file.loadChars());
		log("loaded Chars:"+loaded);
		log("are they equal?"+ loaded.equals(str));
		*/
	}

}


/*
 * I am a teen who is quite skilled about detecting cheats by their talk. I mean detecting cheats is not my job. I just say you probably won't succesfully trick me, even though I am a really gulliable person. In the other hand my main hoby is having control over my computer and that way I learn programming, but I am not planning on getting a fulltime job with it. I am more skilled from majority of people about programming. Also as it looks like I am smarter than the majority of the people. I mean I am really good at understanding topics who are well defined. Also I wast some of my time learning Russian and I plan on learning germanese to. I can really help people to make beginner level aplications so pleas ask to me whenever you need help it would be really funny to do so. Lastly I am nationally Türk and I live In Türkiye, I am muslim.
 * */
