package utils;
public class MD5Utils {
/* *
 * 使用md5的算法进行加密
 * */
	public MD5Utils() {
		md5Init();
	}
	//步骤3:初始化md5缓冲区  
	//初始化md5四个缓冲区ABCD
	long[] count = new long[2];
	public void md5Init() {
		count[0] = 0L;
		count[1] = 0L;
		state[0] = 0x67452301L;
		state[1] = 0xefcdab89L;
		state[2] = 0x98badcfeL;
		state[3] = 0x10325476L;
	}
	//对长度为inputLen的字节数组inbuf进行md5加密
	public void md5Update(byte[] inbuf, int inputLen) {
		//md5加密过程分5步
		//步奏1:增加填充位
		int i, index, partLen;
		byte[] block = new byte[64];
		index = (int) (count[0] >>> 3) & 0x3F;//0x0000 & 0x003f=0x0000变成32位
		if ((count[0] += (inputLen << 3)) < (inputLen << 3))
			count[1]++;
		count[1] += (inputLen >>> 29);//右移29位
		partLen = 64 - index;//64
		if (inputLen >= partLen) {//大于等于64
			//步骤2: 附加消息长度值
			//从字节数组inbuf下标0开始拷贝64个字节至buffer数组0位置
			md5Memcpy(buffer, inbuf, index, 0, partLen);//0-63
			//步骤4:以512位的分组(16个32比特的)为单位处理消息
			md5Transform(buffer);
			for (i = partLen; i + 63 < inputLen; i += 64) {//64之后
				md5Memcpy(block, inbuf, 0, i, 64);
				md5Transform(block);
			}
			index = 0;
		} else{
			i = 0;	
		}
		md5Memcpy(buffer, inbuf, index, i, inputLen - i);
	}
	//从input的inpos开始把len长度的字节拷贝到output的outpos位置开始
	public void md5Memcpy(byte[] output, byte[] input, int outpos, int inpos,
			int len) {
		int i;
		for (i = 0; i < len; i++)
			output[outpos + i] = input[inpos + i];
	}
	//md5的四轮处理:重复应用压缩函数
	public void md5Transform(byte block[]) {
		//a、b、c、d分别为缓冲区A、B、C、D中的字
		long a = state[0], b = state[1], c = state[2], d = state[3];
		long[] x = new long[16];
		//字组X:把当前处理的512比特的分组依次分成16个32比特的字
		//将大小为64的byte类型block数组按顺序合成大小为16的long(64位)类型x数组
		Decode(x, block, 64);
		//第一轮的16步迭代                 常数列表T(1)-T(16)
		a = FF(a, b, c, d, x[0], S11, 0xd76aa478L); 
		d = FF(d, a, b, c, x[1], S12, 0xe8c7b756L); 
		c = FF(c, d, a, b, x[2], S13, 0x242070dbL); 
		b = FF(b, c, d, a, x[3], S14, 0xc1bdceeeL); 
		a = FF(a, b, c, d, x[4], S11, 0xf57c0fafL); 
		d = FF(d, a, b, c, x[5], S12, 0x4787c62aL); 
		c = FF(c, d, a, b, x[6], S13, 0xa8304613L); 
		b = FF(b, c, d, a, x[7], S14, 0xfd469501L); 
		a = FF(a, b, c, d, x[8], S11, 0x698098d8L); 
		d = FF(d, a, b, c, x[9], S12, 0x8b44f7afL); 
		c = FF(c, d, a, b, x[10], S13, 0xffff5bb1L);
		b = FF(b, c, d, a, x[11], S14, 0x895cd7beL);
		a = FF(a, b, c, d, x[12], S11, 0x6b901122L); 
		d = FF(d, a, b, c, x[13], S12, 0xfd987193L); 
		c = FF(c, d, a, b, x[14], S13, 0xa679438eL); 
		b = FF(b, c, d, a, x[15], S14, 0x49b40821L); 
		//第二轮的16步迭代                 常数列表T(17)-T(32)
		a = GG(a, b, c, d, x[1], S21, 0xf61e2562L); 
		d = GG(d, a, b, c, x[6], S22, 0xc040b340L); 
		c = GG(c, d, a, b, x[11], S23, 0x265e5a51L); 
		b = GG(b, c, d, a, x[0], S24, 0xe9b6c7aaL); 
		a = GG(a, b, c, d, x[5], S21, 0xd62f105dL); 
		d = GG(d, a, b, c, x[10], S22, 0x2441453L); 
		c = GG(c, d, a, b, x[15], S23, 0xd8a1e681L); 
		b = GG(b, c, d, a, x[4], S24, 0xe7d3fbc8L); 
		a = GG(a, b, c, d, x[9], S21, 0x21e1cde6L); 
		d = GG(d, a, b, c, x[14], S22, 0xc33707d6L); 
		c = GG(c, d, a, b, x[3], S23, 0xf4d50d87L); 
		b = GG(b, c, d, a, x[8], S24, 0x455a14edL); 
		a = GG(a, b, c, d, x[13], S21, 0xa9e3e905L); 
		d = GG(d, a, b, c, x[2], S22, 0xfcefa3f8L); 
		c = GG(c, d, a, b, x[7], S23, 0x676f02d9L); 
		b = GG(b, c, d, a, x[12], S24, 0x8d2a4c8aL); 
		//第三轮的16步迭代                 常数列表T(33)-T(48)
		a = HH(a, b, c, d, x[5], S31, 0xfffa3942L); 
		d = HH(d, a, b, c, x[8], S32, 0x8771f681L); 
		c = HH(c, d, a, b, x[11], S33, 0x6d9d6122L); 
		b = HH(b, c, d, a, x[14], S34, 0xfde5380cL); 
		a = HH(a, b, c, d, x[1], S31, 0xa4beea44L); 
		d = HH(d, a, b, c, x[4], S32, 0x4bdecfa9L);
		c = HH(c, d, a, b, x[7], S33, 0xf6bb4b60L); 
		b = HH(b, c, d, a, x[10], S34, 0xbebfbc70L); 
		a = HH(a, b, c, d, x[13], S31, 0x289b7ec6L); 
		d = HH(d, a, b, c, x[0], S32, 0xeaa127faL); 
		c = HH(c, d, a, b, x[3], S33, 0xd4ef3085L); 
		b = HH(b, c, d, a, x[6], S34, 0x4881d05L); 
		a = HH(a, b, c, d, x[9], S31, 0xd9d4d039L); 
		d = HH(d, a, b, c, x[12], S32, 0xe6db99e5L); 
		c = HH(c, d, a, b, x[15], S33, 0x1fa27cf8L); 
		b = HH(b, c, d, a, x[2], S34, 0xc4ac5665L); 
		//第四轮的16步迭代                 常数列表T(49)-T(64)
		a = II(a, b, c, d, x[0], S41, 0xf4292244L); 
		d = II(d, a, b, c, x[7], S42, 0x432aff97L); 
		c = II(c, d, a, b, x[14], S43, 0xab9423a7L);
		b = II(b, c, d, a, x[5], S44, 0xfc93a039L);
		a = II(a, b, c, d, x[12], S41, 0x655b59c3L); 
		d = II(d, a, b, c, x[3], S42, 0x8f0ccc92L);
		c = II(c, d, a, b, x[10], S43, 0xffeff47dL); 
		b = II(b, c, d, a, x[1], S44, 0x85845dd1L); 
		a = II(a, b, c, d, x[8], S41, 0x6fa87e4fL); 
		d = II(d, a, b, c, x[15], S42, 0xfe2ce6e0L); 
		c = II(c, d, a, b, x[6], S43, 0xa3014314L); 
		b = II(b, c, d, a, x[13], S44, 0x4e0811a1L); 
		a = II(a, b, c, d, x[4], S41, 0xf7537e82L); 
		d = II(d, a, b, c, x[11], S42, 0xbd3af235L);
		c = II(c, d, a, b, x[2], S43, 0x2ad7d2bbL); 
		b = II(b, c, d, a, x[9], S44, 0xeb86d391L); 
		state[0] += a;
		state[1] += b;
		state[2] += c;
		state[3] += d;
	}

	
	//S11-S44实际上是一个4*4的矩阵
	//循环左移位数s 第一轮第一步
	private static final int S11 = 7;//只读
	private static final int S12 = 12;
	private static final int S13 = 17;
	private static final int S14 = 22;
	private static final int S21 = 5;
	private static final int S22 = 9;
	private static final int S23 = 14;
	private static final int S24 = 20;
	private static final int S31 = 4;
	private static final int S32 = 11;
	private static final int S33 = 16;
	private static final int S34 = 23;
	private static final int S41 = 6;
	private static final int S42 = 10;
	private static final int S43 = 15;
	private static final int S44 = 21;
	private static final byte[] PADDING = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0 };//64个字节(8位)
	private long[] state = new long[4];//四个缓冲区ABCD
	public String digestHexStr;//经过md5转换后输出的16进制字符串
	//步骤5:输出
	//通过md5转换输入的字符串并输出转换后的16进制字符串
	public String getMD5ofStr(String inbuf) {
		md5Init();
		md5Update(inbuf.getBytes(), inbuf.length());
		md5Final();
		digestHexStr = "";
		for (int i = 0; i < 16; i++) {
			digestHexStr += byteHEX(digest[i]);
		}
		return digestHexStr;
	}
	
	//步骤4:以512位的分组(16个32比特的)为单位处理消息
	//字组X,把当前处理的512比特的分组依次分成16个32比特的, 分别记为X[0,1,…,15]
	private byte[] digest = new byte[16];
	private byte[] buffer = new byte[64];
	//F, G, H ,I 是md5基本逻辑函数g
	public long F(long x, long y, long z) {
		return (x & y) | ((~x) & z);
	}
	public long G(long x, long y, long z) {
		return (x & z) | (y & (~z));
	}
	public long H(long x, long y, long z) {
		return x ^ y ^ z;
	}
	public long I(long x, long y, long z) {
		return y ^ (x | (~z));
	}

	//md5压缩函数a<-b+L^s(a+g(b,c,d)+x+ac)
	public long FF(long a, long b, long c, long d, long x, long s, long ac) {
		a += F(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));//带符号左移位数s或无符号右移位数32-s
		a += b;
		return a;
	}
	public long GG(long a, long b, long c, long d, long x, long s, long ac) {
		a += G(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}
	public long HH(long a, long b, long c, long d, long x, long s, long ac) {
		a += H(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}
	public long II(long a, long b, long c, long d, long x, long s, long ac) {
		a += I(b, c, d) + x + ac;
		a = ((int) a << s) | ((int) a >>> (32 - s));
		a += b;
		return a;
	}

	//整理和填写输出结果
	public void md5Final() {
		byte[] bits = new byte[8];
		int index, padLen;
		Encode(bits, count, 8);//将long数组按顺序拆成byte数组,long类型是64bit的,只拆低32bit
		index = (int) (count[0] >>> 3) & 0x3f;//32位0
		padLen = (index < 56) ? (56 - index) : (120 - index);
		md5Update(PADDING, padLen);
		md5Update(bits, 8);
		Encode(digest, state, 16);
	}

	//将long数组按顺序拆成byte数组,long类型是64bit的,只拆低32bit
	public void Encode(byte[] output, long[] input, int len) {
		int i, j;
		for (i = 0, j = 0; j < len; i++, j += 4) {
			output[j] = (byte) (input[i] & 0xffL);
			output[j + 1] = (byte) ((input[i] >>> 8) & 0xffL);//右移一个字节(8位)
			output[j + 2] = (byte) ((input[i] >>> 16) & 0xffL);
			output[j + 3] = (byte) ((input[i] >>> 24) & 0xffL);
		}
	}

	//将byte数组按顺序合成成long(64位)数组
	private void Decode(long[] output, byte[] input, int len) {
		int i, j;
		for (i = 0, j = 0; j < len; i++, j += 4)
			output[i] = b2iu(input[j]) | (b2iu(input[j + 1]) << 8)
					| (b2iu(input[j + 2]) << 16) | (b2iu(input[j + 3]) << 24);
		return;
	}

	//将byte按照不考虑正负号的原则的＂升位＂程序
	public static long b2iu(byte b) {
		return b < 0 ? b & 0x7F + 128 : b;
	}

	//将byte类型的数转换成十六进制的ASCII表示
	public static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
	public static void main(String[] args) {
		MD5Utils key = new MD5Utils();//引入MD5Utils类
		String str="***";
		System.out.println(key.getMD5ofStr(str));
		
	}
}
