/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.springmvc.data;


import com.sg.springmvc.models.GsaProject;
import com.sg.springmvc.models.Project;

import java.util.List;

/**
 *
 * @author yuboz
 */
public interface GsaDao {
    GsaProject getGsaProjectByAccession(String accession);
    List isExistGsaProjectByAccession(String accession);
    GsaProject getGsaProjectById(int id);
    List isExistgetGsaProjectByPrjId(int prj_id);
    GsaProject getGsaProjectByPrjId(int prj_id);
    String getLastGsaProjectAccession();
    List isExistByAccession(String accession);
    List isExistById(int id);
    List<String> getAccessionListByPrjId(int id);

    List<String> getProjectByPrjId(int prjId);
}
