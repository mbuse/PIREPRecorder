/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballantines.avionics.kacars;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 *
 * @author mbuse
 */
public class KAcarsConfig {
    
    public String url = null;
    public String pilotID = null;
    public String password = null;
    public boolean enabled = false;
    public boolean liveUpdateEnabled = false;
    public int liveUpdateIntervalMS = 30000;
    public int timeout = 10000;

    public KAcarsConfig() {
    }

    public KAcarsConfig(KAcarsConfig config) {
        this.url = config.url;
        this.pilotID = config.pilotID;
        this.password = config.password;
        this.enabled = config.enabled;
        this.liveUpdateEnabled = config.liveUpdateEnabled;
        this.liveUpdateIntervalMS = config.liveUpdateIntervalMS;
        this.timeout = config.timeout;
    }
    
    public URL getKAcarsURL() throws MalformedURLException {
        String kacarsUrlAsString = null;
        if (this.url != null && !this.url.startsWith("http://") && !this.url.startsWith("https://")) {
            kacarsUrlAsString = "http://" + this.url;
        }
        else {
            kacarsUrlAsString = this.url;
        }
        URL kacarsUrl = new URL(kacarsUrlAsString);
        String path = kacarsUrl.getPath();
        if (path.length() == 0) {
            kacarsUrlAsString += "/action.php/kacars_free";
        } 
        else if (path.length() == 1) {
            kacarsUrlAsString += "action.php/kacars_free";
        } 
        else {
            return kacarsUrl;
        }
        return new URL(kacarsUrlAsString);
    }
    
    public URL getVAHomeUrl() throws MalformedURLException {
        URL u = getKAcarsURL();
        String path = u.getPath();
        if ("/action.php/kacars_free".equals(path)) {
            path = "";
        }
        else {
            int i = path.indexOf("/action.php/kacars_free");
            path = (i>=0) ? path.substring(0,i)+"/" : "";
        }
        return new URL(u.getProtocol(), u.getHost(),u.getPort(), path);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.url);
        hash = 67 * hash + Objects.hashCode(this.pilotID);
        hash = 67 * hash + Objects.hashCode(this.password);
        hash = 67 * hash + (this.enabled ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.timeout);
        hash = 67 * hash + Objects.hashCode(this.liveUpdateIntervalMS);
        hash = 67 * hash + (this.liveUpdateEnabled ? 1 : 0);
                
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KAcarsConfig other = (KAcarsConfig) obj;
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.pilotID, other.pilotID)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (this.timeout != other.timeout) {
            return false;
        }
        if (this.liveUpdateEnabled != other.liveUpdateEnabled) {
            return false;
        }
        if (this.liveUpdateIntervalMS != other.liveUpdateIntervalMS) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Config{" + "url=" + url
                + ", user=" + pilotID
                + ", password=" + password 
                + ", enabled=" + enabled 
                + ", liveUpdateEnabled=" + liveUpdateEnabled
                + ", liveUpdateIntervalMS=" + liveUpdateIntervalMS + "}";
    }

    
    
    
}
