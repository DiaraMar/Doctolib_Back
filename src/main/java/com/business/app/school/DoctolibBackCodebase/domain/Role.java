package com.business.app.school.DoctolibBackCodebase.domain;

/**
 * * @author Diara Mar
 * * @author www.DiaraMar.com
 * * @version 1.0
 * * @since 1.0
 * <p>
 * String Enumtype is choosen over all the application for a better stability. Rename enum will broke the data model.
 */

public enum Role {
    USER, //link to user
    ADMIN, //link to user
    PATIENT, // link to account
    PRACTICER // link to account
}
