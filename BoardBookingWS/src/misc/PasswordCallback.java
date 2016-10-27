package misc;

import java.io.IOException;
import java.util.logging.Logger;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler {
    
    private final static Logger LOG = Logger.getLogger(PasswordCallback.class.getName());

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
            String alias = pc.getIdentifier();
            LOG.info("Required password for certificate " + alias);
            
            if(alias.equals("booking"))
                pc.setPassword("laipass");
        }

    }

}
