package scramble;

public class letterBlock {
	private int column;
	private int row;
	private boolean isSet;
	
	public letterBlock(int row,int column){
		this.column=column;
		this.row=row;
		this.isSet=false;
	}
	
	public int getColumn(){
		return column;
	}

	public int getRow(){
		return row;
	}

	public boolean isSet() {
		return isSet;
	}

	public void setLetter(boolean isMine) {
		this.isSet = isMine;
	}

}
