/*
 * IRequest.java
 *
 * Created on 2007�~08��12�� �P����, �U��6:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worker;

/**
 *
 * @author Douglas
 */
public interface IRequest {
    
    void init();
    void execute();

    void finish();
    
}
