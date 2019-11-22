package com.mygdx.game.uChumpClasses.Core.Utils;

import java.text.NumberFormat;

import com.mygdx.game.uChumpClasses.iConstants;



public  class uCastingUtils implements iConstants {

	
	
//////////////////////////////////////////////////////////////

// CASTING FUNCTIONS, INSERTED BY PREPROC


/**
* Convert a char to a boolean. 'T', 't', and '1' will become the
* boolean value true, while 'F', 'f', or '0' will become false.
*/
/*
static final public boolean parseBoolean(char what) {
return ((what == 't') || (what == 'T') || (what == '1'));
}
*/

/**
* <p>Convert an integer to a boolean. Because of how Java handles upgrading
* numbers, this will also cover byte and char (as they will upgrade to
* an int without any sort of explicit cast).</p>
* <p>The preprocessor will convert boolean(what) to parseBoolean(what).</p>
* @return false if 0, true if any other number
*/
static final public boolean parseBoolean(int what) {
return (what != 0);
}

/*
// removed because this makes no useful sense
static final public boolean parseBoolean(float what) {
return (what != 0);
}
*/

/**
* Convert the string "true" or "false" to a boolean.
* @return true if 'what' is "true" or "TRUE", false otherwise
*/
static final public boolean parseBoolean(String what) {
return Boolean.parseBoolean(what);
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

/*
// removed, no need to introduce strange syntax from other languages
static final public boolean[] parseBoolean(char what[]) {
boolean outgoing[] = new boolean[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] =
((what[i] == 't') || (what[i] == 'T') || (what[i] == '1'));
}
return outgoing;
}
*/

/**
* Convert a byte array to a boolean array. Each element will be
* evaluated identical to the integer case, where a byte equal
* to zero will return false, and any other value will return true.
* @return array of boolean elements
*/
/*
static final public boolean[] parseBoolean(byte what[]) {
boolean outgoing[] = new boolean[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (what[i] != 0);
}
return outgoing;
}
*/

/**
* Convert an int array to a boolean array. An int equal
* to zero will return false, and any other value will return true.
* @return array of boolean elements
*/
static final public boolean[] parseBoolean(int what[]) {
boolean outgoing[] = new boolean[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (what[i] != 0);
}
return outgoing;
}

/*
// removed, not necessary... if necessary, convert to int array first
static final public boolean[] parseBoolean(float what[]) {
boolean outgoing[] = new boolean[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (what[i] != 0);
}
return outgoing;
}
*/

static final public boolean[] parseBoolean(String what[]) {
boolean outgoing[] = new boolean[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = Boolean.parseBoolean(what[i]);
}
return outgoing;
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public byte parseByte(boolean what) {
return what ? (byte)1 : 0;
}

static final public byte parseByte(char what) {
return (byte) what;
}

static final public byte parseByte(int what) {
return (byte) what;
}

static final public byte parseByte(float what) {
return (byte) what;
}

/*
// nixed, no precedent
static final public byte[] parseByte(String what) {  // note: array[]
return what.getBytes();
}
*/

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public byte[] parseByte(boolean what[]) {
byte outgoing[] = new byte[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = what[i] ? (byte)1 : 0;
}
return outgoing;
}

static final public byte[] parseByte(char what[]) {
byte outgoing[] = new byte[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (byte) what[i];
}
return outgoing;
}

static final public byte[] parseByte(int what[]) {
byte outgoing[] = new byte[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (byte) what[i];
}
return outgoing;
}

static final public byte[] parseByte(float what[]) {
byte outgoing[] = new byte[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (byte) what[i];
}
return outgoing;
}

/*
static final public byte[][] parseByte(String what[]) {  // note: array[][]
byte outgoing[][] = new byte[what.length][];
for (int i = 0; i < what.length; i++) {
outgoing[i] = what[i].getBytes();
}
return outgoing;
}
*/

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

/*
static final public char parseChar(boolean what) {  // 0/1 or T/F ?
return what ? 't' : 'f';
}
*/

static final public char parseChar(byte what) {
return (char) (what & 0xff);
}

static final public char parseChar(int what) {
return (char) what;
}

/*
static final public char parseChar(float what) {  // nonsensical
return (char) what;
}
static final public char[] parseChar(String what) {  // note: array[]
return what.toCharArray();
}
*/

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

/*
static final public char[] parseChar(boolean what[]) {  // 0/1 or T/F ?
char outgoing[] = new char[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = what[i] ? 't' : 'f';
}
return outgoing;
}
*/

static final public char[] parseChar(byte what[]) {
char outgoing[] = new char[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (char) (what[i] & 0xff);
}
return outgoing;
}

static final public char[] parseChar(int what[]) {
char outgoing[] = new char[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (char) what[i];
}
return outgoing;
}

/*
static final public char[] parseChar(float what[]) {  // nonsensical
char outgoing[] = new char[what.length];
for (int i = 0; i < what.length; i++) {
outgoing[i] = (char) what[i];
}
return outgoing;
}
static final public char[][] parseChar(String what[]) {  // note: array[][]
char outgoing[][] = new char[what.length][];
for (int i = 0; i < what.length; i++) {
outgoing[i] = what[i].toCharArray();
}
return outgoing;
}
*/

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public int parseInt(boolean what) {
return what ? 1 : 0;
}

/**
* Note that parseInt() will un-sign a signed byte value.
*/
static final public int parseInt(byte what) {
return what & 0xff;
}

/**
* Note that parseInt('5') is unlike String in the sense that it
* won't return 5, but the ascii value. This is because ((int) someChar)
* returns the ascii value, and parseInt() is just longhand for the cast.
*/
static final public int parseInt(char what) {
return what;
}

/**
* Same as floor(), or an (int) cast.
*/
static final public int parseInt(float what) {
return (int) what;
}

/**
* Parse a String into an int value. Returns 0 if the value is bad.
*/
static final public int parseInt(String what) {
return parseInt(what, 0);
}

/**
* Parse a String to an int, and provide an alternate value that
* should be used when the number is invalid.
*/
static final public int parseInt(String what, int otherwise) {
try {
int offset = what.indexOf('.');
if (offset == -1) {
return Integer.parseInt(what);
} else {
return Integer.parseInt(what.substring(0, offset));
}
} catch (NumberFormatException e) { }
return otherwise;
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public int[] parseInt(boolean what[]) {
int list[] = new int[what.length];
for (int i = 0; i < what.length; i++) {
list[i] = what[i] ? 1 : 0;
}
return list;
}

static final public int[] parseInt(byte what[]) {  // note this unsigns
int list[] = new int[what.length];
for (int i = 0; i < what.length; i++) {
list[i] = (what[i] & 0xff);
}
return list;
}

static final public int[] parseInt(char what[]) {
int list[] = new int[what.length];
for (int i = 0; i < what.length; i++) {
list[i] = what[i];
}
return list;
}

static public int[] parseInt(float what[]) {
int inties[] = new int[what.length];
for (int i = 0; i < what.length; i++) {
inties[i] = (int)what[i];
}
return inties;
}

/**
* Make an array of int elements from an array of String objects.
* If the String can't be parsed as a number, it will be set to zero.
*
* String s[] = { "1", "300", "44" };
* int numbers[] = parseInt(s);
*
* numbers will contain { 1, 300, 44 }
*/
static public int[] parseInt(String what[]) {
return parseInt(what, 0);
}

/**
* Make an array of int elements from an array of String objects.
* If the String can't be parsed as a number, its entry in the
* array will be set to the value of the "missing" parameter.
*
* String s[] = { "1", "300", "apple", "44" };
* int numbers[] = parseInt(s, 9999);
*
* numbers will contain { 1, 300, 9999, 44 }
*/
static public int[] parseInt(String what[], int missing) {
int output[] = new int[what.length];
for (int i = 0; i < what.length; i++) {
try {
output[i] = Integer.parseInt(what[i]);
} catch (NumberFormatException e) {
output[i] = missing;
}
}
return output;
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

/*
static final public float parseFloat(boolean what) {
return what ? 1 : 0;
}
*/

/**
* Convert an int to a float value. Also handles bytes because of
* Java's rules for upgrading values.
*/
static final public float parseFloat(int what) {  // also handles byte
return what;
}

static final public float parseFloat(String what) {
return parseFloat(what, Float.NaN);
}

static final public float parseFloat(String what, float otherwise) {
try {
return Float.parseFloat(what);
} catch (NumberFormatException e) { }

return otherwise;
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

/*
static final public float[] parseFloat(boolean what[]) {
float floaties[] = new float[what.length];
for (int i = 0; i < what.length; i++) {
floaties[i] = what[i] ? 1 : 0;
}
return floaties;
}
static final public float[] parseFloat(char what[]) {
float floaties[] = new float[what.length];
for (int i = 0; i < what.length; i++) {
floaties[i] = (char) what[i];
}
return floaties;
}
*/

static final public float[] parseFloat(byte what[]) {
float floaties[] = new float[what.length];
for (int i = 0; i < what.length; i++) {
floaties[i] = what[i];
}
return floaties;
}

static final public float[] parseFloat(int what[]) {
float floaties[] = new float[what.length];
for (int i = 0; i < what.length; i++) {
floaties[i] = what[i];
}
return floaties;
}

static final public float[] parseFloat(String what[]) {
return parseFloat(what, Float.NaN);
}

static final public float[] parseFloat(String what[], float missing) {
float output[] = new float[what.length];
for (int i = 0; i < what.length; i++) {
try {
output[i] = Float.parseFloat(what[i]);
} catch (NumberFormatException e) {
output[i] = missing;
}
}
return output;
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public String str(boolean x) {
return String.valueOf(x);
}

static final public String str(byte x) {
return String.valueOf(x);
}

static final public String str(char x) {
return String.valueOf(x);
}

static final public String str(int x) {
return String.valueOf(x);
}

static final public String str(float x) {
return String.valueOf(x);
}

// . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .

static final public String[] str(boolean x[]) {
String s[] = new String[x.length];
for (int i = 0; i < x.length; i++) s[i] = String.valueOf(x[i]);
return s;
}

static final public String[] str(byte x[]) {
String s[] = new String[x.length];
for (int i = 0; i < x.length; i++) s[i] = String.valueOf(x[i]);
return s;
}

static final public String[] str(char x[]) {
String s[] = new String[x.length];
for (int i = 0; i < x.length; i++) s[i] = String.valueOf(x[i]);
return s;
}

static final public String[] str(int x[]) {
String s[] = new String[x.length];
for (int i = 0; i < x.length; i++) s[i] = String.valueOf(x[i]);
return s;
}

static final public String[] str(float x[]) {
String s[] = new String[x.length];
for (int i = 0; i < x.length; i++) s[i] = String.valueOf(x[i]);
return s;
}


//////////////////////////////////////////////////////////////

// INT NUMBER FORMATTING

static public String nf(float num) {
int inum = (int) num;
if (num == inum) {
return str(inum);
}
return str(num);
}

static public String[] nf(float[] nums) {
String[] outgoing = new String[nums.length];
for (int i = 0; i < nums.length; i++) {
outgoing[i] = nf(nums[i]);
}
return outgoing;
}

/**
* Integer number formatter.
*/

static private NumberFormat int_nf;
static private int int_nf_digits;
static private boolean int_nf_commas;

/**
* ( begin auto-generated from nf.xml )
*
* Utility function for formatting numbers into strings. There are two
* versions, one for formatting floats and one for formatting ints. The
* values for the <b>digits</b>, <b>left</b>, and <b>right</b> parameters
* should always be positive integers.<br /><br />As shown in the above
* example, <b>nf()</b> is used to add zeros to the left and/or right of a
* number. This is typically for aligning a list of numbers. To
* <em>remove</em> digits from a floating-point number, use the
* <b>int()</b>, <b>ceil()</b>, <b>floor()</b>, or <b>round()</b>
* functions.
*
* ( end auto-generated )
* @webref data:string_functions
* @param nums the numbers to format
* @param digits number of digits to pad with zero
* @see PApplet#nfs(float, int, int)
* @see PApplet#nfp(float, int, int)
* @see PApplet#nfc(float, int)
* @see <a href="https://processing.org/reference/intconvert_.html">int(float)</a>
*/

static public String[] nf(int nums[], int digits) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nf(nums[i], digits);
}
return formatted;
}

/**
* @param num the number to format
*/
static public String nf(int num, int digits) {
if ((int_nf != null) &&
(int_nf_digits == digits) &&
!int_nf_commas) {
return int_nf.format(num);
}

int_nf = NumberFormat.getInstance();
int_nf.setGroupingUsed(false); // no commas
int_nf_commas = false;
int_nf.setMinimumIntegerDigits(digits);
int_nf_digits = digits;
return int_nf.format(num);
}

/**
* ( begin auto-generated from nfc.xml )
*
* Utility function for formatting numbers into strings and placing
* appropriate commas to mark units of 1000. There are two versions, one
* for formatting ints and one for formatting an array of ints. The value
* for the <b>digits</b> parameter should always be a positive integer.
* <br/><br/>
* For a non-US locale, this will insert periods instead of commas, or
* whatever is apprioriate for that region.
*
* ( end auto-generated )
* @webref data:string_functions
* @param nums the numbers to format
* @see PApplet#nf(float, int, int)
* @see PApplet#nfp(float, int, int)
* @see PApplet#nfs(float, int, int)
*/
static public String[] nfc(int nums[]) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfc(nums[i]);
}
return formatted;
}


/**
* @param num the number to format
*/
static public String nfc(int num) {
if ((int_nf != null) &&
(int_nf_digits == 0) &&
int_nf_commas) {
return int_nf.format(num);
}

int_nf = NumberFormat.getInstance();
int_nf.setGroupingUsed(true);
int_nf_commas = true;
int_nf.setMinimumIntegerDigits(0);
int_nf_digits = 0;
return int_nf.format(num);
}


/**
* number format signed (or space)
* Formats a number but leaves a blank space in the front
* when it's positive so that it can be properly aligned with
* numbers that have a negative sign in front of them.
*/

/**
* ( begin auto-generated from nfs.xml )
*
* Utility function for formatting numbers into strings. Similar to
* <b>nf()</b> but leaves a blank space in front of positive numbers so
* they align with negative numbers in spite of the minus symbol. There are
* two versions, one for formatting floats and one for formatting ints. The
* values for the <b>digits</b>, <b>left</b>, and <b>right</b> parameters
* should always be positive integers.
*
* ( end auto-generated )
* @webref data:string_functions
* @param num the number to format
* @param digits number of digits to pad with zeroes
* @see PApplet#nf(float, int, int)
* @see PApplet#nfp(float, int, int)
* @see PApplet#nfc(float, int)
*/
static public String nfs(int num, int digits) {
return (num < 0) ? nf(num, digits) : (' ' + nf(num, digits));
}

/**
* @param nums the numbers to format
*/
static public String[] nfs(int nums[], int digits) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfs(nums[i], digits);
}
return formatted;
}

//

/**
* number format positive (or plus)
* Formats a number, always placing a - or + sign
* in the front when it's negative or positive.
*/
/**
* ( begin auto-generated from nfp.xml )
*
* Utility function for formatting numbers into strings. Similar to
* <b>nf()</b> but puts a "+" in front of positive numbers and a "-" in
* front of negative numbers. There are two versions, one for formatting
* floats and one for formatting ints. The values for the <b>digits</b>,
* <b>left</b>, and <b>right</b> parameters should always be positive integers.
*
* ( end auto-generated )
* @webref data:string_functions
* @param num the number to format
* @param digits number of digits to pad with zeroes
* @see PApplet#nf(float, int, int)
* @see PApplet#nfs(float, int, int)
* @see PApplet#nfc(float, int)
*/
static public String nfp(int num, int digits) {
return (num < 0) ? nf(num, digits) : ('+' + nf(num, digits));
}
/**
* @param nums the numbers to format
*/
static public String[] nfp(int nums[], int digits) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfp(nums[i], digits);
}
return formatted;
}



//////////////////////////////////////////////////////////////

// FLOAT NUMBER FORMATTING

static private NumberFormat float_nf;
static private int float_nf_left, float_nf_right;
static private boolean float_nf_commas;

/**
* @param left number of digits to the left of the decimal point
* @param right number of digits to the right of the decimal point
*/
static public String[] nf(float nums[], int left, int right) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nf(nums[i], left, right);
}
return formatted;
}

static public String nf(float num, int left, int right) {
if ((float_nf != null) &&
(float_nf_left == left) &&
(float_nf_right == right) &&
!float_nf_commas) {
return float_nf.format(num);
}

float_nf = NumberFormat.getInstance();
float_nf.setGroupingUsed(false);
float_nf_commas = false;

if (left != 0) float_nf.setMinimumIntegerDigits(left);
if (right != 0) {
float_nf.setMinimumFractionDigits(right);
float_nf.setMaximumFractionDigits(right);
}
float_nf_left = left;
float_nf_right = right;
return float_nf.format(num);
}

/**
* @param right number of digits to the right of the decimal point
*/
static public String[] nfc(float nums[], int right) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfc(nums[i], right);
}
return formatted;
}

static public String nfc(float num, int right) {
if ((float_nf != null) &&
(float_nf_left == 0) &&
(float_nf_right == right) &&
float_nf_commas) {
return float_nf.format(num);
}

float_nf = NumberFormat.getInstance();
float_nf.setGroupingUsed(true);
float_nf_commas = true;

if (right != 0) {
float_nf.setMinimumFractionDigits(right);
float_nf.setMaximumFractionDigits(right);
}
float_nf_left = 0;
float_nf_right = right;
return float_nf.format(num);
}


/**
* @param left the number of digits to the left of the decimal point
* @param right the number of digits to the right of the decimal point
*/
static public String[] nfs(float nums[], int left, int right) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfs(nums[i], left, right);
}
return formatted;
}

static public String nfs(float num, int left, int right) {
return (num < 0) ? nf(num, left, right) :  (' ' + nf(num, left, right));
}

/**
* @param left the number of digits to the left of the decimal point
* @param right the number of digits to the right of the decimal point
*/
static public String[] nfp(float nums[], int left, int right) {
String formatted[] = new String[nums.length];
for (int i = 0; i < formatted.length; i++) {
formatted[i] = nfp(nums[i], left, right);
}
return formatted;
}

static public String nfp(float num, int left, int right) {
return (num < 0) ? nf(num, left, right) :  ('+' + nf(num, left, right));
}



//////////////////////////////////////////////////////////////

// HEX/BINARY CONVERSION


/**
* ( begin auto-generated from hex.xml )
*
* Converts a byte, char, int, or color to a String containing the
* equivalent hexadecimal notation. For example color(0, 102, 153) will
* convert to the String "FF006699". This function can help make your geeky
* debugging sessions much happier.
* <br/> <br/>
* Note that the maximum number of digits is 8, because an int value can
* only represent up to 32 bits. Specifying more than eight digits will
* simply shorten the string to eight anyway.
*
* ( end auto-generated )
* @webref data:conversion
* @param value the value to convert
* @see PApplet#unhex(String)
* @see PApplet#binary(byte)
* @see PApplet#unbinary(String)
*/
static final public String hex(byte value) {
return hex(value, 2);
}

static final public String hex(char value) {
return hex(value, 4);
}

static final public String hex(int value) {
return hex(value, 8);
}
/**
* @param digits the number of digits (maximum 8)
*/
static final public String hex(int value, int digits) {
String stuff = Integer.toHexString(value).toUpperCase();
if (digits > 8) {
digits = 8;
}

int length = stuff.length();
if (length > digits) {
return stuff.substring(length - digits);

} else if (length < digits) {
return "00000000".substring(8 - (digits-length)) + stuff;
}
return stuff;
}

/**
* ( begin auto-generated from unhex.xml )
*
* Converts a String representation of a hexadecimal number to its
* equivalent integer value.
*
* ( end auto-generated )
*
* @webref data:conversion
* @param value String to convert to an integer
* @see PApplet#hex(int, int)
* @see PApplet#binary(byte)
* @see PApplet#unbinary(String)
*/
static final public int unhex(String value) {
// has to parse as a Long so that it'll work for numbers bigger than 2^31
return (int) (Long.parseLong(value, 16));
}

//

/**
* Returns a String that contains the binary value of a byte.
* The returned value will always have 8 digits.
*/
static final public String binary(byte value) {
return binary(value, 8);
}

/**
* Returns a String that contains the binary value of a char.
* The returned value will always have 16 digits because chars
* are two bytes long.
*/
static final public String binary(char value) {
return binary(value, 16);
}

/**
* Returns a String that contains the binary value of an int. The length
* depends on the size of the number itself. If you want a specific number
* of digits use binary(int what, int digits) to specify how many.
*/
static final public String binary(int value) {
return binary(value, 32);
}

/*
* Returns a String that contains the binary value of an int.
* The digits parameter determines how many digits will be used.
*/

/**
* ( begin auto-generated from binary.xml )
*
* Converts a byte, char, int, or color to a String containing the
* equivalent binary notation. For example color(0, 102, 153, 255) will
* convert to the String "11111111000000000110011010011001". This function
* can help make your geeky debugging sessions much happier.
* <br/> <br/>
* Note that the maximum number of digits is 32, because an int value can
* only represent up to 32 bits. Specifying more than 32 digits will simply
* shorten the string to 32 anyway.
*
* ( end auto-generated )
* @webref data:conversion
* @param value value to convert
* @param digits number of digits to return
* @see PApplet#unbinary(String)
* @see PApplet#hex(int,int)
* @see PApplet#unhex(String)
*/
static final public String binary(int value, int digits) {
String stuff = Integer.toBinaryString(value);
if (digits > 32) {
digits = 32;
}

int length = stuff.length();
if (length > digits) {
return stuff.substring(length - digits);

} else if (length < digits) {
int offset = 32 - (digits-length);
return "00000000000000000000000000000000".substring(offset) + stuff;
}
return stuff;
}


/**
* ( begin auto-generated from unbinary.xml )
*
* Converts a String representation of a binary number to its equivalent
* integer value. For example, unbinary("00001000") will return 8.
*
* ( end auto-generated )
* @webref data:conversion
* @param value String to convert to an integer
* @see PApplet#binary(byte)
* @see PApplet#hex(int,int)
* @see PApplet#unhex(String)
*/
static final public int unbinary(String value) {
return Integer.parseInt(value, 2);
}
	
	
	
}
