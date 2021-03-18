package ca.javateacher.tipcalculatorver3s;

public class TipCalculator {
  private float mTip;
  private float mBill;

  public TipCalculator(float newTip, float newBill) {
    setTip(newTip);
    setBill(newBill);
  }

  public float getTip() {
    return mTip;
  }

  public float getBill() {
    return mBill;
  }

  public void setTip(float newTip) {
    if (newTip > 0)
      mTip = newTip;
  }

  public void setBill(float newBill) {
    if (newBill > 0)
      mBill = newBill;
  }

  public float tipAmount() {
    return mBill * mTip;
  }

  public float totalAmount() {
    return mBill + tipAmount();
  }
}
