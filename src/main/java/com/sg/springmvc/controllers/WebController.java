/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.springmvc.controllers;

import com.sg.springmvc.data.*;
import com.sg.springmvc.models.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 *
 * @author yuboz
 */
@CrossOrigin
@RestController
@RequestMapping("/api/gsa")
public class WebController {

    private final GsaDao gsaDao;
    private final PublicationDao publicationDao;
    private final GsaHumanStudyDao gsaHumanStudyDao;
    private final GsaProjectDao gsaProjectDao;
    private final OmixDao omixDao;
    private final JournalDao journalDao;

    public WebController(GsaDao gsaDao, PublicationDao publicationDao, GsaHumanStudyDao gsaHumanStudyDao, GsaProjectDao gsaProjectDao, OmixDao omixDao, JournalDao journalDao) {
        this.gsaDao = gsaDao;
        this.publicationDao = publicationDao;
        this.gsaHumanStudyDao = gsaHumanStudyDao;
        this.gsaProjectDao = gsaProjectDao;
        this.omixDao = omixDao;
        this.journalDao = journalDao;
    }
    //测试Mapping
    @GetMapping("/{id}")
    public ResponseEntity<GsaProject> findProjectById(@PathVariable int id){
        GsaProject result = gsaDao.getGsaProjectById(id);
        return  ResponseEntity.ok(result);
    }
    @GetMapping("/gsaP")
    public GsaProject findProjectByAccession(@RequestBody GsaProject gsaProject){
        return gsaDao.getGsaProjectByAccession(gsaProject.getAccession());
    }
    @GetMapping("/publication")
    public ResponseEntity<Publication> findPublicationByTitle(@RequestBody Publication publication){
        String title = publication.getTitle();
        //测试数据库
        Publication publication1 = publicationDao.getPublicationByTitle(title);
        //测试获取pub
        Publication publication2 = queryPaperFromPubMed(title);
        return ResponseEntity.ok(publication2);
    }
    @GetMapping("/publication/{id}")
    public ResponseEntity<Publication> findPublicationById(@PathVariable int id){
        Publication result = publicationDao.getPublicationById(id);
        return  ResponseEntity.ok(result);
    }
    @GetMapping("/gsah/{id}")
    public ResponseEntity<Study> findStudyById(@PathVariable int id){
        Study result= gsaHumanStudyDao.getStudyByStudyId(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/gsah")
    public Study findGsaHumanStudyByAccession(@RequestBody Study study){
        String accession = study.getAccession();
        study = gsaHumanStudyDao.getStudyByAccession(accession);
        return study;
    }
    @GetMapping("/gsahuman/project")
    public int findGsaHumanStudyByProjectAccession(@RequestBody Study study){
        int prj_id = gsaHumanStudyDao.getPrjIdByPrjAccession(study.getPrj_accession());
        return prj_id;
    }
    @GetMapping("/gsahuman/accessions")
    public List<String> findGsaAccessionsByProjectAccession(@RequestBody Study study){
        List<String> gsaAccessions = gsaHumanStudyDao.getGsaAccessionsByPrjAccession(study.getPrj_accession());
        return gsaAccessions;
    }
    @GetMapping("/publicationArticle")
    public Publication findPublicationByArticleTitle(@RequestBody String articleTitle){
        return publicationDao.getPublicationByArticleTitle(articleTitle);
    }
    @GetMapping("/publicationList")
    public List<String> findArticleTitleByGsaLinked(){
        return publicationDao.getArticleTitleByGsaLinked();
    }
    @GetMapping("/project")
    public Project findProjectByPrjAccession(@RequestBody String accession){
        Project project= gsaProjectDao.getProjectByAccession(accession);
        return project;
    }
    @GetMapping("/gsaprj/{prj_id}")
    public List<String> findProjectPrjId(@PathVariable int prj_id){
//        System.out.println(gsaDao.getProjectByPrjId(prj_id));
        return gsaDao.getProjectByPrjId(prj_id);
    }
    @GetMapping("/gsahprj/{prj_id}")
    public List<String> findGsaHumanAccessionListByPrjId(@PathVariable int prj_id){
//        System.out.println(prj_id);
//        System.out.println(gsaHumanStudyDao.getStudyAccessionByPrjId(prj_id).size());
        return gsaHumanStudyDao.getStudyAccessionByPrjId(prj_id);
    }
    @GetMapping("/omix/accession")
    public Omix findOmxiByAccession(@RequestBody String accession){
        return omixDao.getOmixProjectByOmixAccession(accession);
    }
    @GetMapping("/omix/title")
    public Omix findOmxiByTitle(@RequestBody String title){
        return omixDao.getOmixProjectByOmixTitle(title);
    }
    @GetMapping("/omix/prj/{prj_id}")
    public List<String> findOmxiByPrjId(@PathVariable int prj_id){
        return omixDao.getOmixAccessionListByPrjId(prj_id);
    }
    @GetMapping("/omix/{id}")
    public Omix findOmxiByAccession(@PathVariable int id){
        return omixDao.getOmixProjectByOmixId(id);
    }
    @GetMapping("/omix/prjaccession")
    public List<String> findOmixCodeListByPrjAccession(@RequestBody String prj_accession){
        return omixDao.getOmixAccessionListByPrjAccession(prj_accession);
    }
    @GetMapping("/journal")
    public List<String > findAllJournalMap(){
        return journalDao.getAllJournalTitle();
    }
    @GetMapping("/journal/letter")
    public List<String > findJournalNameByLetter(@RequestBody String letter){
        return journalDao.getJournalTitleByFirstLetter(letter);
    }
    @PostMapping("/addpublication")
    public void addPublication(@RequestBody Publication publication){
        publicationDao.addPublication(publication);
    }
    @GetMapping("/journal/{id}")
    public Journal findJournalById(@PathVariable int id){
        return journalDao.getJournalById(id);
    }

    //实际业务
    @GetMapping("/journalname")
    public Map<String,Integer>  findJournalNameAndId(){
        List<Journal> journalList = journalDao.getAllJournal();
        Map<String,Integer> map = new HashMap<>();
        for (Journal journal : journalList){
            map.put(journal.getJournal().replace(" ","").toUpperCase(),journal.getId());
        }
        return map;
    }
    @PostMapping("/publicationList")
    public List<Publication> findPublicationByTitle(@RequestBody List<Publication> publications){
        List<Publication> publicationList=new ArrayList<>();
        List<String> titlesInDb = publicationDao.getArticleTitleByGsaLinked();
        for(Publication publication: publications){
            String title = publication.getTitle();
            if(titlesInDb.contains(title)){
                Publication publicationInDb =publicationDao.getPublicationByTitle(title);
                publicationInDb.setGsaAccessions(publicationInDb.getGsaAccession());
                publicationList.add(publicationInDb);
            }else {
                publicationList.add(queryPaperFromPubMed(title));
                try {
                    //sleep time 10000->1000
                    Thread.sleep(1000);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return publicationList;
    }
    //待测试
//    @PostMapping("/gsah/create")
//    public List<Publication> addPublication(@RequestBody List<Publication> publications){
//        //返回前端的值
//        List<Publication> publicationList = new ArrayList<>();
//        //gsa accession 为空事返回值
//        final String NODATAFOUND="No Data found";
//        //accession 的status 不为3
//        final String WRONGSTATUS="This accession already in DB, however status is not 3";
//        //审编人员输入accession查不到时返回值
//        final String DESCRIPTION ="No Such accession in Database(GSA,GSAHuman,Omix)";
//
//        for(Publication publication : publications){
//            String gsaAccessions = publication.getGsaAccessions();
//
//            if (gsaAccessions !=null&&gsaAccessions!=""){
//                //分离出多个输入的accession
//                String[]gsaAccessionList = gsaAccessions.split(",");
//                //每个accession 比较起始字母
//                for (String accession : gsaAccessionList) {
//                    if (accession.startsWith("CRA")){
//                        Publication publicationInList = new Publication(publication);
//                        //判断是否在project中
//                        if(gsaDao.isExistByAccession(accession).size()>0){
////                            System.out.println(accession+"在project中");
//                            //判断此accession是否存为status 3
//                            if(gsaDao.isExistGsaProjectByAccession(accession).size()>0){
////                                System.out.println("这是CRA在库中的accession： "+ accession);
//                                GsaProject gsaProject = gsaDao.getGsaProjectByAccession(accession);
//                                publicationInList.setPrj_id(gsaProject.getPrj_id());
//                                publicationInList.setGsaAccession(accession);
//                                //添加到数据库
//                                //publicationDao.addPublication(publicationInList);
//                            }else {
////                                System.out.println("status不是3的： "+ accession);
//                                publicationInList.setGsaAccession(WRONGSTATUS +": "+accession);
//                            }
//                        }else {
////                            System.out.println("这是CRA不在库中的accession： "+ accession);
//                            publicationInList.setGsaAccession(DESCRIPTION +": "+accession);
//                        }
//                        publicationList.add(publicationInList);
//                    }
//                    else if(accession.startsWith("subCRA")){
//                        Publication publicationInList = new Publication(publication);
//                        int craId=WebController.getNumberFromString(accession);
//                        //判断是否存在craId
//                        if(gsaDao.isExistById(craId).size()>0){
////                            System.out.println("这里是subcra号 在cra库中"+accession);
//                            GsaProject gsaProject = gsaDao.getGsaProjectById(craId);
////                            System.out.println(accession + "   " + craId);
//                            //判断此id是否存在accession
//                            if(gsaProject.getAccession()!=""&&gsaProject.getAccession()!=null){
////                                System.out.println("这里是subcra 且此cra_id 有accession "+accession);
//                                publicationInList.setPrj_id(gsaProject.getPrj_id());
//                                publicationInList.setGsaAccession(accession);
//                                //publicationDao.addPublication(publicationInList);
//                            }else {
////                                System.out.println("这里是subcra 且此cra_id 没有accession "+accession);
//                                publicationInList.setGsaAccession(NODATAFOUND+": "+accession);
//                            }
//                        }else {
////                            System.out.println("这里是subcra号 不存在cra库中" +accession);
//                            publicationInList.setGsaAccession(DESCRIPTION+": "+accession);
//                        }
//                        publicationList.add(publicationInList);
//                    }
//                    else if(accession.startsWith("PRJ")){
//                        if(gsaProjectDao.isExistByAccession(accession).size()>0){
////                            System.out.println("此PRJ号存在于 project中 ："+accession);
//                            Project project=gsaProjectDao.getProjectByAccession(accession);
//                            publication.setPrj_id(project.getPrj_id());
//                            //判断是否在cra表中
//                            if (gsaDao.getProjectByPrjId(project.getPrj_id()).size()>0){
////                                System.out.println(accession+"在cra中");
//                                Publication publicationInList = new Publication(publication);
//                                //status 是否为3
//                                List<String> accessionList = gsaDao.getProjectByPrjId(project.getPrj_id());
//                                for (String gsaAccession : accessionList){
//                                    publicationInList.setGsaAccession(gsaAccession);
////                                    System.out.println("通过此"+ accession+"获得的accession"+gsaAccession);
////                                    publicationDao.addPublication(publicationInList);
//                                    publicationList.add(publicationInList);
//                                    //publicationDao.addPublication(publicationInList);
//                                }
//                            }
//                            //判断是否在study表中
//                            else if (gsaHumanStudyDao.getStudyAccessionByPrjId(project.getPrj_id()).size()>0){
////                                System.out.println("这个accession"+ accession +"在study中");
//                                Publication publicationInList = new Publication(publication);
//                                //这里改了
//                                List<String> accessionList = gsaHumanStudyDao.getGsaAccessionsByPrjAccession(accession);
//                                for (String gsaHumanAccession: accessionList){
////                                    System.out.println("通过此"+ accession+"获得的accession"+gsaHumanAccession);
//                                    publicationInList.setGsaAccession(gsaHumanAccession);
////                                    publicationDao.addPublication(publicationInList);
//                                    publicationList.add(publicationInList);
//                                }
//                            }
//                            //判断是否在omix表中
//                            else if (omixDao.getOmixAccessionListByPrjId(project.getPrj_id()).size()>0) {
////                                System.out.println(accession+"存在于omix表中");
//                                Publication publicationInList = new Publication(publication);
//                                List<String> omixCodeList = omixDao.getOmixAccessionListByPrjAccession(accession);
//                                for(String omixCode : omixCodeList ){
////                                    System.out.println("通过此"+accession+"获得的accession"+omixCode);
//                                    publicationInList.setGsaAccession(omixCode);
////                                    publicationDao.addPublication(publicationInList);
//                                    publicationList.add(publicationInList);
//                                }
//                            }
//                        }else {
////                            System.out.println("此PRJ号不存在于 cra 和 study"+accession);
//                            Publication publicationInList = new Publication(publication);
//                            publicationInList.setGsaAccession(DESCRIPTION+": "+accession);
////                            publicationDao.addPublication(publicationInList);
//                            publicationList.add(publicationInList);
//                        }
//                    }
//                    else if(accession.startsWith("HRA")) {
//                        Publication publicationInList = new Publication(publication);
//                        //判断此accession是否存在库中
//                        if(gsaHumanStudyDao.isExistByAccession(accession).size()>0){
////                            System.out.println("这是RAH在库中的accession： "+ accession);
//                            //判断status是否为3
//                            if(gsaHumanStudyDao.isExistGetEditorialGsaHumanStudyByAccession(accession).size()>0){
//                                Study study = gsaHumanStudyDao.getStudyByAccession(accession);
//                                publicationInList.setPrj_id(study.getPrj_id());
//                                publicationInList.setGsaAccession(accession);
//                                //publicationDao.addPublication(publicationInList);
//                            }else {
//                                publicationInList.setGsaAccession(WRONGSTATUS +": "+accession);
//                            }
//                        }else {
////                            System.out.println("这是RAH不在库中的accession： "+ accession);
//                            publicationInList.setGsaAccessions(DESCRIPTION+": "+accession);
//                        }
////                        publicationDao.addPublication(publicationInList);
//                        publicationList.add(publicationInList);
//                    }
//                    else if (accession.startsWith("subHRA")) {
//                        Publication publicationInList = new Publication(publication);
//                        int studyId=WebController.getNumberFromString(accession);
//                        //判断此id是否存在库中
//                        if(gsaHumanStudyDao.isExistById(studyId).size()>0){
////                            System.out.println("这里是subhra号 在study库中"+accession);
//                            Study study = gsaHumanStudyDao.getStudyByStudyId(studyId);
//                            //判断此id的accession是否为空
//                            if(study.getAccession()!=""&&study.getAccession()!=null){
////                                System.out.println("这里是subhra号 在study库中 且此 study_id 的accession不为空"+accession);
//                                publicationInList.setPrj_id(study.getPrj_id());
//                                publicationInList.setGsaAccession(accession);
//                                //publicationDao.addPublication(publicationInList);
//                            }else {
////                                System.out.println("这里是subhra号 在study库中 且此 study_id 的accession为空"+accession);
//                                publicationInList.setGsaAccession(NODATAFOUND +": "+accession);
//                            }
//                        }else {
////                            System.out.println("这里是subhra号 不在study库中"+accession);
//                            publicationInList.setGsaAccession(DESCRIPTION+": "+accession);
//                        }
//                        publicationList.add(publicationInList);
//                    }
//                    //omix 开头的accession  还要添加prj 开头的omix 检索
//                    else if (accession.startsWith("OMIX")) {
//                        Publication publicationInList = new Publication(publication);
//                        //判断accession 是否存在于omix
//                        if (omixDao.isExistgetOmixProjectByOmixAccession(accession).size()>0){
//                            //判断status 不为3
//                            if(omixDao.isExistGetEditorialOmixCodeByOmixCode(accession).size()>0){
//                                Omix omix = omixDao.getOmixProjectByOmixAccession(accession);
//                                publicationInList.setPrj_id(omix.getPrj_id());
//                                publicationInList.setGsaAccession(accession);
//                                //publicationDao.addPublication(publicationInList);
//                            }else {
//                                publicationInList.setGsaAccession(WRONGSTATUS +": "+accession);
//                            }
//                        } else {
//                            publicationInList.setGsaAccessions(DESCRIPTION+": "+accession);
//                        }
//                        publicationList.add(publicationInList);
//                    }
//                    //不是规定开头
//                    else {
//                        Publication publicationInList = new Publication(publication);
//                        publicationInList.setGsaAccession(DESCRIPTION+": "+accession);
//                        publicationList.add(publicationInList);
//                    }
//                }
//            }else {
//                Publication publicationInList = new Publication(publication);
//                publicationInList.setGsaAccession(DESCRIPTION);
//                publicationList.add(publicationInList);
//            }
//        }
//        return publicationList;
//    }
    @PostMapping("/creatpublication")
    public List<List<Publication>> addPublicationFromFontEnd(@RequestBody List<Publication> publications){
        //返回前端的值
        List<List<Publication>> result = new ArrayList<>();
        List<Publication> publicationList = new ArrayList<>();
        List<Publication> wrongPublicationList = new ArrayList<>();
        //gsa accession 为空事返回值
        final String NODATAFOUND="No Data found";
        //accession 的status 不为3
        final String WRONGSTATUS="Wrong accession status";
        //审编人员输入accession查不到时返回值
        final String DESCRIPTION ="No accession in DB";
        //gsaAccession 存在问题
        final String NULLACCESSION = "null accessions";

        for(Publication publication : publications){
            Journal journal = journalDao.getJournalById(publication.getJournal_id());
            publication.setJournalName(journal.getJournal());
            String gsaAccessions = publication.getGsaAccessions();
            String wrongAccession = "";
            if (gsaAccessions !=null&&gsaAccessions!=""){
                //分离出多个输入的accession
                String[]gsaAccessionList = gsaAccessions.split(",");
                //每个accession 比较起始字母
                for (String accession : gsaAccessionList) {
                    if (accession.startsWith("CRA")){
                        Publication publicationInList = new Publication(publication);
                        //判断是否在project中
                        if(gsaDao.isExistByAccession(accession).size()>0){
//                            System.out.println(accession+"在project中");
                            //判断此accession是否存为status 3
                            if(gsaDao.isExistGsaProjectByAccession(accession).size()>0){
//                                System.out.println("这是CRA在库中的accession： "+ accession);
                                GsaProject gsaProject = gsaDao.getGsaProjectByAccession(accession);
                                publicationInList.setPrj_id(gsaProject.getPrj_id());
                                publicationInList.setGsaAccession(accession);
                                //添加到数据库
                                //publicationDao.addPublication(publicationInList);
                                publicationList.add(publicationInList);
                            }else {
//                                System.out.println("status不是3的： "+ accession);
                                wrongAccession += WRONGSTATUS +": "+accession+",";
                            }
                        }else {
//                            System.out.println("这是CRA不在库中的accession： "+ accession);
                            publicationInList.setGsaAccessions(DESCRIPTION +": "+accession);
                        }
                    }
                    else if(accession.startsWith("subCRA")){
                        Publication publicationInList = new Publication(publication);
                        int craId=WebController.getNumberFromString(accession);
                        //判断是否存在craId
                        if(gsaDao.isExistById(craId).size()>0){
//                            System.out.println("这里是subcra号 在cra库中"+accession);
                            GsaProject gsaProject = gsaDao.getGsaProjectById(craId);
//                            System.out.println(accession + "   " + craId);
                            //判断此id是否存在accession
                            if(gsaProject.getAccession()!=""&&gsaProject.getAccession()!=null){
//                                System.out.println("这里是subcra 且此cra_id 有accession "+accession);
                                publicationInList.setPrj_id(gsaProject.getPrj_id());
                                publicationInList.setGsaAccession(accession);
                                //publicationDao.addPublication(publicationInList);
                                publicationList.add(publicationInList);
                            }else {
//                                System.out.println("这里是subcra 且此cra_id 没有accession "+accession);
                                wrongAccession+=NODATAFOUND+": "+accession+",";
                            }
                        }else {
//                            System.out.println("这里是subcra号 不存在cra库中" +accession);
                            wrongAccession=DESCRIPTION+": "+accession+",";
                        }
                    }
                    else if(accession.startsWith("PRJ")){
                        if(gsaProjectDao.isExistByAccession(accession).size()>0){
//                            System.out.println("此PRJ号存在于 project中 ："+accession);
                            Project project=gsaProjectDao.getProjectByAccession(accession);
                            publication.setPrj_id(project.getPrj_id());
                            //判断是否在cra表中
                            if (gsaDao.getProjectByPrjId(project.getPrj_id()).size()>0){
//                                System.out.println(accession+"在cra中");
                                Publication publicationInList = new Publication(publication);
                                //status 是否为3
                                List<String> accessionList = gsaDao.getProjectByPrjId(project.getPrj_id());
                                for (String gsaAccession : accessionList){
                                    publicationInList.setGsaAccession(gsaAccession);
//                                    System.out.println("通过此"+ accession+"获得的accession"+gsaAccession);
//                                    publicationDao.addPublication(publicationInList);
                                    publicationList.add(publicationInList);
                                    //publicationDao.addPublication(publicationInList);
                                }
                            }
                            //判断是否在study表中
                            else if (gsaHumanStudyDao.getStudyAccessionByPrjId(project.getPrj_id()).size()>0){
//                                System.out.println("这个accession"+ accession +"在study中");
                                Publication publicationInList = new Publication(publication);
                                //这里改了
                                List<String> accessionList = gsaHumanStudyDao.getGsaAccessionsByPrjAccession(accession);
                                for (String gsaHumanAccession: accessionList){
//                                    System.out.println("通过此"+ accession+"获得的accession"+gsaHumanAccession);
                                    publicationInList.setGsaAccession(gsaHumanAccession);
//                                    publicationDao.addPublication(publicationInList);
                                    publicationList.add(publicationInList);
                                }
                            }
                            //判断是否在omix表中
                            else if (omixDao.getOmixAccessionListByPrjId(project.getPrj_id()).size()>0) {
//                                System.out.println(accession+"存在于omix表中");
                                Publication publicationInList = new Publication(publication);
                                List<String> omixCodeList = omixDao.getOmixAccessionListByPrjAccession(accession);
                                for(String omixCode : omixCodeList ){
//                                    System.out.println("通过此"+accession+"获得的accession"+omixCode);
                                    publicationInList.setGsaAccession(omixCode);
//                                    publicationDao.addPublication(publicationInList);
                                    publicationList.add(publicationInList);
                                }
                            }
                        }else {
//                            System.out.println("此PRJ号不存在于 cra 和 study"+accession);
//                            publicationDao.addPublication(publicationInList);
                            wrongAccession+=DESCRIPTION+": "+accession+",";
                        }
                    }
                    else if(accession.startsWith("HRA")) {
                        Publication publicationInList = new Publication(publication);
                        //判断此accession是否存在库中
                        if(gsaHumanStudyDao.isExistByAccession(accession).size()>0){
//                            System.out.println("这是RAH在库中的accession： "+ accession);
                            //判断status是否为3
                            if(gsaHumanStudyDao.isExistGetEditorialGsaHumanStudyByAccession(accession).size()>0){
                                Study study = gsaHumanStudyDao.getStudyByAccession(accession);
                                publicationInList.setPrj_id(study.getPrj_id());
                                publicationInList.setGsaAccession(accession);
                                publicationList.add(publicationInList);
                                //publicationDao.addPublication(publicationInList);
                            }else {
                                wrongAccession+=WRONGSTATUS+": "+accession+",";
                            }
                        }else {
//                            System.out.println("这是RAH不在库中的accession： "+ accession);
                            wrongAccession+=DESCRIPTION+": "+accession+",";
                        }
                    }
                    else if (accession.startsWith("subHRA")) {
                        Publication publicationInList = new Publication(publication);
                        int studyId=WebController.getNumberFromString(accession);
                        //判断此id是否存在库中
                        if(gsaHumanStudyDao.isExistById(studyId).size()>0){
//                            System.out.println("这里是subhra号 在study库中"+accession);
                            Study study = gsaHumanStudyDao.getStudyByStudyId(studyId);
                            //判断此id的accession是否为空
                            if(study.getAccession()!=""&&study.getAccession()!=null){
//                                System.out.println("这里是subhra号 在study库中 且此 study_id 的accession不为空"+accession);
                                publicationInList.setPrj_id(study.getPrj_id());
                                publicationInList.setGsaAccession(accession);
                                publicationList.add(publicationInList);
                                //publicationDao.addPublication(publicationInList);
                            }else {
//                                System.out.println("这里是subhra号 在study库中 且此 study_id 的accession为空"+accession);
                                wrongAccession += NODATAFOUND+": "+accession+",";
                            }
                        }else {
//                            System.out.println("这里是subhra号 不在study库中"+accession);
                            wrongAccession+=DESCRIPTION+": "+accession+",";
                        }
                    }
                    //omix 开头的accession  还要添加prj 开头的omix 检索
                    else if (accession.startsWith("OMIX")) {
                        Publication publicationInList = new Publication(publication);
                        //判断accession 是否存在于omix
                        if (omixDao.isExistgetOmixProjectByOmixAccession(accession).size()>0){
                            //判断status 不为3
                            if(omixDao.isExistGetEditorialOmixCodeByOmixCode(accession).size()>0){
                                Omix omix = omixDao.getOmixProjectByOmixAccession(accession);
                                publicationInList.setPrj_id(omix.getPrj_id());
                                publicationInList.setGsaAccession(accession);
                                publicationList.add(publicationInList);
                                //publicationDao.addPublication(publicationInList);
                            }else {
                                wrongAccession+=WRONGSTATUS+": "+accession+",";
                            }
                        } else {
                            wrongAccession+=DESCRIPTION+": "+accession+",";
                        }
                    }
                    //不是规定开头
                    else {
                        wrongAccession+=DESCRIPTION+": "+accession+",";
                    }
                }
            }else {
                wrongAccession+= NULLACCESSION;
            }
            //System.out.println(publication.getTitle() +": "+wrongAccession);
            Publication publicationInList = new Publication(publication);
            publicationInList.setGsaAccessions(wrongAccession);
            wrongPublicationList.add(publicationInList);
        }
        System.out.println(publicationList);
        result.add(wrongPublicationList);
        result.add(publicationList);
        return result;
    }
    public static List<String> queryIdsByArticleTitle(String title){
        List<String> pubmedIdList = new ArrayList<>();
        String baseUrl = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
        try {

            String term = "esearch.fcgi?db=pubmed&term=" + URLEncoder.encode(title, "UTF8");
            String urlF = baseUrl + term;

            SAXReader sax = new SAXReader();
            Document doc = sax.read(new URL(urlF));
            Element root = doc.getRootElement();

            Element idListElement = root.element("IdList");
            for(Iterator i = idListElement.elementIterator(); i.hasNext();) {
                Element ele = (Element) i.next();
//	            		System.out.println(ele.toString());
//	            		System.out.println(ele.getTextTrim());
                pubmedIdList.add(ele.getTextTrim());
            }

        }catch (MalformedURLException ex1) {
//        		    ex1.printStackTrace();
            System.out.println("ERROR: MalformedURLException: title: "+title);
        }catch(IOException ioex) {
//            		System.out.println("ERROR: IO Exception.");
            System.out.println("ERROR: IOException: title: "+title);
//	        		ioex.printStackTrace();
        } catch (DocumentException ex) {
//	        		ex.printStackTrace();
            System.out.println("ERROR: DocumentException: title: "+title);
            //Logger.getLogger(SearchPaper.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception e) {
//	        		e.printStackTrace();
            System.out.println("ERROR: Exception: title: "+title);
        }
        if(pubmedIdList.size()>0) {
            for(String str:pubmedIdList) {
                System.out.println("pmid:"+str);
            }
        }

        return pubmedIdList;

    }

    public static List<Publication> queryByID(String id) throws MalformedURLException{
        List<Publication> publicationList = new ArrayList<>();
        String baseUrl = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/";
        try {

            String term = "efetch.fcgi?db=pubmed&id=" + id + "&retmode=xml";
            String urlF = baseUrl + term;

            SAXReader sax = new SAXReader();
            Document doc = sax.read(new URL(urlF));
            Element root = doc.getRootElement();

            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Publication publication = new Publication();
                Element childE1 = (Element) i.next();
                Element childE2 = childE1.element("MedlineCitation");
                String pmid = childE2.elementTextTrim("PMID");

                publication.setPmid(pmid);
                Element articleNode = childE2.element("Article");
                Element journalNode = articleNode.element("Journal");
                Element pubDateNode = journalNode.element("JournalIssue").element("PubDate");

                Iterator elocationIdNodes = articleNode.elementIterator("ELocationID");
                while(elocationIdNodes.hasNext()) {
                    Element clocationIdNode=(Element)elocationIdNodes.next();
                    if(clocationIdNode.attribute("EIdType").getValue().equals("doi")) {
                        publication.setDoi(clocationIdNode.getTextTrim());
                    }

                }

                String y;
                String m;
                String day;

                // get pubdate
                y = pubDateNode.elementTextTrim("Year");
                m = pubDateNode.elementTextTrim("Month");
                day = pubDateNode.elementTextTrim("Day");

                if (y==null) y = "";
                publication.setYear(y);
                publication.setMonth(m);
                publication.setDay(day);

                String volume = journalNode.element("JournalIssue")
                        .elementTextTrim("Volume");
                String issue = journalNode.element("JournalIssue")
                        .elementTextTrim("Issue");
                String at = articleNode.elementTextTrim("ArticleTitle");
                String title = journalNode.elementTextTrim("Title");

                if (volume==null) volume="";
                if (issue==null) issue="";
                publication.setVolume(volume);
                publication.setIssue(issue);
                publication.setTitle(at);
                publication.setJournalName(title);
                publication.setPmid(id);


                publicationList.add(publication);

            }

        } catch (DocumentException ex) {
            System.out.println("ERROR: DocumentException: pubmed_id: "+id);
//        		ex.printStackTrace();
            //Logger.getLogger(SearchPaper.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ioex) {
            System.out.println("ERROR: IOException: pubmed_id: "+id);
//        		ioex.printStackTrace();
        }catch(Exception e) {
            System.out.println("ERROR: Exception: pubmed_id: "+id);
        }

        return publicationList;
        }

    public static Publication queryPaperFromPubMed(String title){
        List<String> pubmedIds = WebController.queryIdsByArticleTitle(title);
        if(pubmedIds!=null && pubmedIds.size()>0) {
            if(pubmedIds.size()==1) {
                String pubmedId = pubmedIds.get(0);
                try {
                    List<Publication> publicationList = WebController.queryByID(pubmedId);
                    if (publicationList!=null && publicationList.size()>0){
                        if(publicationList.size()==1){
                            Publication tt = (Publication) (publicationList.get(0));
                            String articleTitle = tt.getTitle() ;
                            if(articleTitle.endsWith(".")) {
                                tt.setTitle(articleTitle.substring(0,articleTitle.length()-1));
                            }
                            return tt;
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
        Publication publication = new Publication();
        publication.setTitle(title);
        return publication;
    }

    public static int getNumberFromString(String accession){
        String result ="";
        accession=accession.trim();
        if(accession!=null &&!"".equals(accession)){
            for(int i=0;i<accession.length();i++){
                if(accession.charAt(i)>=48&& accession.charAt(i)<=57){
                    result+= accession.charAt(i);
                }
            }
        }
        return Integer.parseInt(result);
    };
}
