package org.bcit.comp2522.project;

// Java program to illustrate synchronous callback
interface OnEventListener {
  // this can be any type of method
  void onEvent();
}

class B {

  private OnEventListener mListener; // listener field

  // setting the listener
  public void registerOnEventListener(OnEventListener mListener)
  {
    this.mListener = mListener;
  }

  // my synchronous task
  public void doStuff()
  {

    // perform any operation
    System.out.println("Performing callback before synchronous Task");

    // check if listener is registered.
    if (this.mListener != null) {

      // invoke the callback method of class A
      mListener.onEvent();
    }
  }

  // Driver Function
  public static void main(String[] args)
  {
    B obj = new B();
    OnEventListener mListener = new A();
    obj.registerOnEventListener(mListener);
    obj.doStuff();
  }
}

class A implements OnEventListener {

  @Override
  public void onEvent()
  {
    System.out.println("Performing callback after synchronous Task");
    // perform some routine operation
  }
  // some class A methods
}