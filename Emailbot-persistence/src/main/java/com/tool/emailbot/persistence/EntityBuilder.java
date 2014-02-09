/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence;

import java.io.Serializable;

/**
 *
 * @author edgar
 */
public interface EntityBuilder<T extends Entidad> extends Serializable{
    T build();
}
