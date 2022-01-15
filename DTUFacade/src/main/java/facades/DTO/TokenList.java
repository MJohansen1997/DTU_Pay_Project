package facades.DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class TokenList implements Serializable {

    private static final long serialVersionUID = 9023222981284806610L;
    private ArrayList<String> tokens;

    public TokenList() {
        tokens = new ArrayList<>();
    }



    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }
}
