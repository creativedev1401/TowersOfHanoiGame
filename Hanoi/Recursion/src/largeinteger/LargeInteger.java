package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
  public LLNode<Integer> head; // head of the list
  private int size; // size (i.e. number of digits)
  private String str = "";

  // Returns size
  public int size() { 
    return size; 
  }
  
  // Returns the linked list (used only for JUnit test purpose)
  public LLNode<Integer> getList() { 
    return head; 
  }

  public LargeInteger() {
    head = null; 
    size = 0;
  }

  /** Constructor that takes a String as input and constructs the linked list.
   *  You can assume that the input is guaranteed to be valid: i.e. every character
   *  in the string is between '0' and '9', and the first character is never '0'
   *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
   *  to convert the character at index i to the integer value of that digit.
   *  Remember: the list nodes must be in reverse order as the characters in the string.
   */
  public LargeInteger(String input) {
      // TODO
	  size = input.length();
	  //head = new LLNode<Integer>();
	  head = new LLNode<Integer>(Integer.parseInt(Character.toString(input.charAt(input.length()-1))), null);
	  LLNode<Integer> tail = head;
	  for (int i = input.length() - 1; i >= 0; i--) {
		  tail.data = input.charAt(i) - '0';
		  if (i != 0) {
			  tail.link = new LLNode<Integer>();
			  tail = tail.link;
		  }
	  }
  }

  /** Divide *this* large integer by 10 and return this.
   *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
   */
  public LargeInteger divide10() {
    // TODO
	if (head.link == null) {
		head.data = 0;
		return this;
	} else {
		head = head.link;
	    size -= 1;
	    return this;
	}
  }

  /** Multiply *this* large integer by 10 and return this.
   *  For example, 23*10 = 230, 0*10 = 0 etc.
   */
  public LargeInteger multiply10() {
        // TODO
	if (size() == 1 && head.data == 0) {
		return this;
	}
	LLNode<Integer> newNode = new LLNode<Integer>(0, head);
	head = newNode;
	size += 1;
    return this;
  }

  /** Returns a *new* LargeInteger object representing the sum of this large integer
   *  and another one (given by that). Your code must correctly handle cases such as
   *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
   *  carry over at the highest digit (e.g. 9999+2=10001).
   */
  
  public LargeInteger add(LargeInteger that) {
	  //TODO
	  LargeInteger results = new LargeInteger();
	  LLNode<Integer> newOne = new LLNode<Integer>(0, null);
	  LLNode<Integer> resultsTail = results.head;
	  LLNode<Integer> thatTail = that.head;
	  LLNode<Integer> thisTail = this.head;
	  
	  while(thatTail != null || thisTail != null || newOne.data != 0) {
		  
		  if (thisTail == null && thatTail != null) {
			  
			  Integer sum = thatTail.data + newOne.data;
			  
			  if (sum > 9) {
				  
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum%10, null);
				  
				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(1, null);
				  newOne = newOne.link;
			  } else {
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum, null);

				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(0, null);
				  newOne = newOne.link;
			  }
			   
		  } else if (thatTail == null && thisTail != null) {

			  Integer sum = thisTail.data + newOne.data;
			  
			  if (sum > 9) {
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum%10, null);

				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(1, null);
				  newOne = newOne.link;
			  } else {
				  
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum, null);

				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(0, null);
				  newOne = newOne.link;
			  }
			   
		  } else if ((thatTail == null && thisTail == null) && newOne.data == 1){
			  
			  if (newOne.data == 1) {
				  LLNode<Integer> insertNode = new LLNode<Integer>(1, null);
				  resultsTail.link = insertNode;
				  resultsTail = insertNode;
				  results.size += 1;
			  }
			  
		  } else {
			  //if neither are null

			  Integer sum = thatTail.data + thisTail.data + newOne.data;
			  
			  if (sum > 9) {
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum%10, null);

				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(1, null);
				  newOne = newOne.link;
			  } else {
				  LLNode<Integer> insertNode = new LLNode<Integer>(sum, null);

				  if (results.head == null) {
					  results.head = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  } else {
					  resultsTail.link = insertNode;
					  resultsTail = insertNode;
					  results.size += 1;
				  }
				  
				  newOne.link = new LLNode<Integer>(0, null);
				  newOne = newOne.link;
			  }
			   
		  }
		  if (thisTail == null && thatTail == null) {
			  break;
		  } else if (thisTail == null && thatTail != null) {
			  thatTail = thatTail.link;
		  } else if (thatTail == null && thisTail != null) {
			  thisTail = thisTail.link;
		  } else {
			  thatTail = thatTail.link;
			  thisTail = thisTail.link;
		  }
	  }
	  return results;
  }
  
  public LargeInteger addeded(LargeInteger that) {
        // TODO
	  LargeInteger results = new LargeInteger();
	  LLNode<Integer> newOne = new LLNode<Integer>(0, null);
	  LLNode<Integer> resultsTail = results.head;
	  LLNode<Integer> thatHead = that.head;
      LLNode<Integer> tempHead = this.head;
      while (tempHead != null || thatHead != null) {
    	  if (tempHead == null) {
    		  LLNode<Integer> newNode = new LLNode<Integer>(thatHead.data, null);
    		  if (thatHead.data > 9) {
    			  LLNode<Integer> appendData = new LLNode<Integer>(thatHead.data%10, null);
    			  if (results.head == null) {
    				  results.head = appendData;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  if (resultsTail.link == null) {
    					  resultsTail.link = appendData;
        				  resultsTail = appendData;
        				  results.size += 1;
    				  } else {
    					  resultsTail.link.data += thatHead.data%10;
    				  }
    			  }
    			  LLNode<Integer> newOneNode = new LLNode<Integer>(1, null);
    			  resultsTail.link = newOneNode;
    			  results.size += 1;
    		  } else {
    			  if (results.head == null) {
    				  results.head = newNode;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  resultsTail.link = newNode;
    				  resultsTail = newNode;
    				  results.size += 1;
    			  }
    		  }
    		  thatHead = thatHead.link;
    	  } else if (thatHead == null) {
    		  LLNode<Integer> newNode = new LLNode<Integer>(tempHead.data, null);
    		  if (tempHead.data > 9) {
    			  LLNode<Integer> appendData = new LLNode<Integer>(tempHead.data%10, null);
    			  if (results.head == null) {
    				  results.head = appendData;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  if (resultsTail.link == null) {
    					  resultsTail.link = appendData;
        				  resultsTail = appendData;
        				  results.size += 1;
    				  } else {
    					  resultsTail.link.data += tempHead.data%10;
    				  }
    			  }
    			  LLNode<Integer> newOneNode = new LLNode<Integer>(1, null);
    			  resultsTail.link = newOneNode;
    			  results.size += 1;
    		  } else {
    			  if (results.head == null) {
    				  results.head = newNode;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  resultsTail.link = newNode;
    				  resultsTail = newNode;
    				  results.size += 1;
    			  }
    		  }
    		  tempHead = tempHead.link;
    	  } else {
    		  //neither are null
    		  int sum = tempHead.data + thatHead.data;
    		  LLNode<Integer> newNode = new LLNode<Integer>(sum, null);
    		  if ((sum) > 9) {
    			  LLNode<Integer> appendData = new LLNode<Integer>(sum%10, null);
    			  if (results.head == null) {
    				  results.head = appendData;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  if (resultsTail.link == null) {
    					  resultsTail.link = appendData;
        				  resultsTail = appendData;
        				  results.size += 1;
    				  } else {
    					  resultsTail.link.data += sum%10;
    				  }
    			  }
    			  LLNode<Integer> newOneNode = new LLNode<Integer>(1, null);
    			  resultsTail.link = newOneNode;
    			  results.size += 1;
    		  } else {
    			  if (results.head == null) {
    				  results.head = newNode;
    				  resultsTail = results.head;
    				  results.size += 1;
    			  } else  {
    				  resultsTail.link = newNode;
    				  resultsTail = newNode;
    				  results.size += 1;
    			  }
    		  }
    		  tempHead = tempHead.link;
    		  thatHead = thatHead.link;
    	  }
      }
      return results;
  }

  /** Returns a new LargeInteger object representing the result of multiplying
   *  this large integer with a non-negative integer x. You can assume x is either
   *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
   *  above to accomplish the 'multiply'.
   */
  public LargeInteger multiply(int x) {
        // TODO
	 LargeInteger result = null;
	 if (x == 0) {
		 result = new LargeInteger("0");
	 } else {
		 for (int i = 1; i <= x; i++) {
	    	 if (result == null) {
	    		 result = new LargeInteger(this.toString());
	    	 } else {
	        	 result = result.add(this);
	    	 }
	     }
	 }
	 return result;
  }

  /** Recursive method that converts the list referenced by curr_node back to
   *  a string representing the integer. Think about what's the base case and
   *  what it should return. Then think about what it should return in non-base case.
   *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
   */
  private String toString(LLNode<Integer> node) {
        // TODO
	  if (node == null) {
		  return "";
	  } else {
		  return toString(node.link) + Integer.toString(node.data);
	  }
  }

  /** Convert this list back to a string representing the large integer.
   *  This is a public method that jump-starts the call to the recursive method above.
   */
  public String toString() {
    return toString(head);
  }

  /** Recursive method to compute factorial. */
  public static LargeInteger factorial(int n) {
    if (n == 0) {
      return new LargeInteger("1");
    }
    return factorial(n - 1).multiply(n);
  }

  /** Recursive method to compute power. */
  public static LargeInteger pow(int x, int y) {
    if (y == 0) {
      return new LargeInteger("1");
    }
    return pow(x, y - 1).multiply(x);
  }
  public LargeInteger added(LargeInteger that) {
      // TODO
	  LargeInteger results = new LargeInteger();
	  //LLNode<Integer> resultsHead = results.head;
	  LLNode<Integer> resultsTail = results.head;
	  LLNode<Integer> thatHead = that.head;
    LLNode<Integer> tempHead = head;
    while (tempHead != null || thatHead != null) {
  	  if (tempHead == null) {
  		  LLNode<Integer> newNode = new LLNode<Integer>(thatHead.data, null);
  		  if (thatHead.data > 9) {
  			  if (thatHead.link == null) {
  				  LLNode<Integer> newOneNode = new LLNode<Integer>(1, null);
  				  thatHead.link = newOneNode;
  			  } else {
  				  thatHead.link.data += 1;
  			  }
  			  LLNode<Integer> appendData = new LLNode<Integer>(thatHead.data%10, null);
  			  if (results.head == null) {
  				  results.head = appendData;
  				  resultsTail = results.head;
  				  results.size += 1;
  			  } else  {
  				  resultsTail.link = appendData;
  				  resultsTail = appendData;
  				  results.size += 1;
  			  }
  		  } else {
  			  if (results.head == null) {
  				  results.head = newNode;
  				  resultsTail = results.head;
  				  results.size += 1;
  			  } else  {
  				  resultsTail.link = newNode;
  				  resultsTail = newNode;
  				  results.size += 1;
  			  }
  		  }
  		  thatHead = thatHead.link;
  	  } else if (thatHead == null) {
  		  LLNode<Integer> newNode = new LLNode<Integer>(tempHead.data, null);
  		  if (results.head == null) {
				  results.head = newNode;
				  resultsTail = results.head;
				  results.size += 1;
			  } else  {
				  resultsTail.link = newNode;
				  resultsTail = newNode;
				  results.size += 1;
			  }
  		  tempHead = tempHead.link;
  	  } else {
  		  //neither are null
  		  int sum = tempHead.data + thatHead.data;
  		  LLNode<Integer> newNode = new LLNode<Integer>(sum, null);
  		  if ((sum) > 9) {
  			  if (thatHead.link == null) {
  				  LLNode<Integer> newOneNode = new LLNode<Integer>(1, null);
  				  thatHead.link = newOneNode;
  			  } else {
  				  thatHead.link.data += 1;
  			  }
  			  LLNode<Integer> appendData = new LLNode<Integer>(sum%10, null);
  			  if (results.head == null) {
  				  results.head = appendData;
  				  resultsTail = results.head;
  				  results.size += 1;
  			  } else  {
  				  resultsTail.link = appendData;
  				  resultsTail = appendData;
  				  results.size += 1;
  			  }
  		  } else {
  			  if (results.head == null) {
  				  results.head = newNode;
  				  resultsTail = results.head;
  				  results.size += 1;
  			  } else  {
  				  resultsTail.link = newNode;
  				  resultsTail = newNode;
  				  results.size += 1;
  			  }
  		  }
  		  tempHead = tempHead.link;
  		  thatHead = thatHead.link;
  	  }
    }
    return results;
}
}

