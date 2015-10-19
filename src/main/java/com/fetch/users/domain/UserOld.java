package com.fetch.users.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOld {

	private BigDecimal geniusenterprise;

	private String geniuslearningby;

	private String geniusparietal;

	private Integer geniusmusicrank;

	private String geniusminatureper;

	private String geniusaddressline2;

	private String geniusaffectivestyle;

	private BigDecimal geniuscq;

	private String geniusaddressline1;

	private Integer geniusmispatialrank;

	private Integer geniusfinemotorrank;

	private Integer geniusvisualrank;

	private Integer geniusmathlogicrank;

	private Integer geniusmimathrank;

	private String geniusmanagementpattern;

	private Integer geniusmimusicrank;

	private String geniusfrontal;

	private String geniuscharinner;

	private String geniuscharouter;

	private Integer geniusmilanguagerank;

	private Integer geniusimaginerank;

	private BigDecimal geniusauditorylearning;

	private String geniuscognitivestyle;

	private Integer geniusleadershiprank;

	private BigDecimal geniustfrc;

	private Boolean scantakenyes;

	private String geniusreflectivestyle;

	private String geniusaddremarks;

	private BigDecimal geniuskinasteticlearning;

	private BigDecimal geniusminaturerank;

	private BigDecimal geniusinvestigate;

	private String geniusmimusicper;

	private Integer geniusmiinterrank;

	private String geniuscriticalstyle;

	private String geniusreflective;

	private Integer geniusmikinasteticrank;

	private String geniusgender;

	private String geniuspreviousremarks;

	private String geniusmiinterper;

	private String geniusocipital;

	private String geniusemail;

	private String geniusrightpercentage;

	private Integer geniusmiintrarank;

	private String geniusprefrontal;

	private String geniusleadershippattern;

	private String geniusparent;

	private Integer geniuslanguagelistenrank;

	private String geniusdos;

	private BigDecimal geniuseq;

	private String geniusgrossmotorpattern;

	private String geniusname;

	private String geniusmilanguageper;

	private String geniusreferredby;

	private String geniusvisualpattern;

	private String geniustemporal;

	private String geniusfinemotorpattern;

	private BigDecimal geniusvisuallearning;

	private String geniusmikinasteticper;

	private Integer geniusobservationrank;

	private Integer geniusatdleft;

	private String geniusthinkright;

	private String geniusdob;

	private BigDecimal geniusrealistic;

	private String geniusmotive;

	private String geniusthinkleft;

	private String geniusimaginepattern;

	private String geniuslanguagelistenpattern;

	private BigDecimal geniusartistic;

	private String geniusmusicpattern;

	private String geniusmathlogicpattern;

	private String geniusobservationpattern;

	private Integer geniusgrossmotorrank;

	private String geniusleftpercentage;

	private Integer geniusatdright;

	private String geniusmiintraper;

	public BigDecimal getGeniusenterprise() {
		return geniusenterprise;
	}

	public void setGeniusenterprise(BigDecimal geniusenterprise) {
		this.geniusenterprise = geniusenterprise;
	}

	public String getGeniuslearningby() {
		return geniuslearningby;
	}

	public void setGeniuslearningby(String geniuslearningby) {
		this.geniuslearningby = geniuslearningby;
	}

	public String getGeniusparietal() {
		return geniusparietal;
	}

	public void setGeniusparietal(String geniusparietal) {
		this.geniusparietal = geniusparietal;
	}

	public Integer getGeniusmusicrank() {
		return geniusmusicrank;
	}

	public void setGeniusmusicrank(Integer geniusmusicrank) {
		this.geniusmusicrank = geniusmusicrank;
	}

	public String getGeniusminatureper() {
		return geniusminatureper;
	}

	public void setGeniusminatureper(String geniusminatureper) {
		this.geniusminatureper = geniusminatureper;
	}

	public String getGeniusaddressline2() {
		return geniusaddressline2;
	}

	public void setGeniusaddressline2(String geniusaddressline2) {
		this.geniusaddressline2 = geniusaddressline2;
	}

	public String getGeniusaffectivestyle() {
		return geniusaffectivestyle;
	}

	public void setGeniusaffectivestyle(String geniusaffectivestyle) {
		this.geniusaffectivestyle = geniusaffectivestyle;
	}

	public BigDecimal getGeniuscq() {
		return geniuscq;
	}

	public void setGeniuscq(BigDecimal geniuscq) {
		this.geniuscq = geniuscq;
	}

	public String getGeniusaddressline1() {
		return geniusaddressline1;
	}

	public void setGeniusaddressline1(String geniusaddressline1) {
		this.geniusaddressline1 = geniusaddressline1;
	}

	public Integer getGeniusmispatialrank() {
		return geniusmispatialrank;
	}

	public void setGeniusmispatialrank(Integer geniusmispatialrank) {
		this.geniusmispatialrank = geniusmispatialrank;
	}

	public Integer getGeniusfinemotorrank() {
		return geniusfinemotorrank;
	}

	public void setGeniusfinemotorrank(Integer geniusfinemotorrank) {
		this.geniusfinemotorrank = geniusfinemotorrank;
	}

	public Integer getGeniusvisualrank() {
		return geniusvisualrank;
	}

	public void setGeniusvisualrank(Integer geniusvisualrank) {
		this.geniusvisualrank = geniusvisualrank;
	}

	public Integer getGeniusmathlogicrank() {
		return geniusmathlogicrank;
	}

	public void setGeniusmathlogicrank(Integer geniusmathlogicrank) {
		this.geniusmathlogicrank = geniusmathlogicrank;
	}

	public Integer getGeniusmimathrank() {
		return geniusmimathrank;
	}

	public void setGeniusmimathrank(Integer geniusmimathrank) {
		this.geniusmimathrank = geniusmimathrank;
	}

	public String getGeniusmanagementpattern() {
		return geniusmanagementpattern;
	}

	public void setGeniusmanagementpattern(String geniusmanagementpattern) {
		this.geniusmanagementpattern = geniusmanagementpattern;
	}

	public Integer getGeniusmimusicrank() {
		return geniusmimusicrank;
	}

	public void setGeniusmimusicrank(Integer geniusmimusicrank) {
		this.geniusmimusicrank = geniusmimusicrank;
	}

	public String getGeniusfrontal() {
		return geniusfrontal;
	}

	public void setGeniusfrontal(String geniusfrontal) {
		this.geniusfrontal = geniusfrontal;
	}

	public String getGeniuscharinner() {
		return geniuscharinner;
	}

	public void setGeniuscharinner(String geniuscharinner) {
		this.geniuscharinner = geniuscharinner;
	}

	public String getGeniuscharouter() {
		return geniuscharouter;
	}

	public void setGeniuscharouter(String geniuscharouter) {
		this.geniuscharouter = geniuscharouter;
	}

	public Integer getGeniusmilanguagerank() {
		return geniusmilanguagerank;
	}

	public void setGeniusmilanguagerank(Integer geniusmilanguagerank) {
		this.geniusmilanguagerank = geniusmilanguagerank;
	}

	public Integer getGeniusimaginerank() {
		return geniusimaginerank;
	}

	public void setGeniusimaginerank(Integer geniusimaginerank) {
		this.geniusimaginerank = geniusimaginerank;
	}

	public BigDecimal getGeniusauditorylearning() {
		return geniusauditorylearning;
	}

	public void setGeniusauditorylearning(BigDecimal geniusauditorylearning) {
		this.geniusauditorylearning = geniusauditorylearning;
	}

	public String getGeniuscognitivestyle() {
		return geniuscognitivestyle;
	}

	public void setGeniuscognitivestyle(String geniuscognitivestyle) {
		this.geniuscognitivestyle = geniuscognitivestyle;
	}

	public Integer getGeniusleadershiprank() {
		return geniusleadershiprank;
	}

	public void setGeniusleadershiprank(Integer geniusleadershiprank) {
		this.geniusleadershiprank = geniusleadershiprank;
	}

	public BigDecimal getGeniustfrc() {
		return geniustfrc;
	}

	public void setGeniustfrc(BigDecimal geniustfrc) {
		this.geniustfrc = geniustfrc;
	}

	public Boolean getScantakenyes() {
		return scantakenyes;
	}

	public void setScantakenyes(Boolean scantakenyes) {
		this.scantakenyes = scantakenyes;
	}

	public String getGeniusreflectivestyle() {
		return geniusreflectivestyle;
	}

	public void setGeniusreflectivestyle(String geniusreflectivestyle) {
		this.geniusreflectivestyle = geniusreflectivestyle;
	}

	public String getGeniusaddremarks() {
		return geniusaddremarks;
	}

	public void setGeniusaddremarks(String geniusaddremarks) {
		this.geniusaddremarks = geniusaddremarks;
	}

	public BigDecimal getGeniuskinasteticlearning() {
		return geniuskinasteticlearning;
	}

	public void setGeniuskinasteticlearning(BigDecimal geniuskinasteticlearning) {
		this.geniuskinasteticlearning = geniuskinasteticlearning;
	}

	public BigDecimal getGeniusminaturerank() {
		return geniusminaturerank;
	}

	public void setGeniusminaturerank(BigDecimal geniusminaturerank) {
		this.geniusminaturerank = geniusminaturerank;
	}

	public BigDecimal getGeniusinvestigate() {
		return geniusinvestigate;
	}

	public void setGeniusinvestigate(BigDecimal geniusinvestigate) {
		this.geniusinvestigate = geniusinvestigate;
	}

	public String getGeniusmimusicper() {
		return geniusmimusicper;
	}

	public void setGeniusmimusicper(String geniusmimusicper) {
		this.geniusmimusicper = geniusmimusicper;
	}

	public Integer getGeniusmiinterrank() {
		return geniusmiinterrank;
	}

	public void setGeniusmiinterrank(Integer geniusmiinterrank) {
		this.geniusmiinterrank = geniusmiinterrank;
	}

	public String getGeniuscriticalstyle() {
		return geniuscriticalstyle;
	}

	public void setGeniuscriticalstyle(String geniuscriticalstyle) {
		this.geniuscriticalstyle = geniuscriticalstyle;
	}

	public String getGeniusreflective() {
		return geniusreflective;
	}

	public void setGeniusreflective(String geniusreflective) {
		this.geniusreflective = geniusreflective;
	}

	public Integer getGeniusmikinasteticrank() {
		return geniusmikinasteticrank;
	}

	public void setGeniusmikinasteticrank(Integer geniusmikinasteticrank) {
		this.geniusmikinasteticrank = geniusmikinasteticrank;
	}

	public String getGeniusgender() {
		return geniusgender;
	}

	public void setGeniusgender(String geniusgender) {
		this.geniusgender = geniusgender;
	}

	public String getGeniuspreviousremarks() {
		return geniuspreviousremarks;
	}

	public void setGeniuspreviousremarks(String geniuspreviousremarks) {
		this.geniuspreviousremarks = geniuspreviousremarks;
	}

	public String getGeniusmiinterper() {
		return geniusmiinterper;
	}

	public void setGeniusmiinterper(String geniusmiinterper) {
		this.geniusmiinterper = geniusmiinterper;
	}

	public String getGeniusocipital() {
		return geniusocipital;
	}

	public void setGeniusocipital(String geniusocipital) {
		this.geniusocipital = geniusocipital;
	}

	public String getGeniusemail() {
		return geniusemail;
	}

	public void setGeniusemail(String geniusemail) {
		this.geniusemail = geniusemail;
	}

	public String getGeniusrightpercentage() {
		return geniusrightpercentage;
	}

	public void setGeniusrightpercentage(String geniusrightpercentage) {
		this.geniusrightpercentage = geniusrightpercentage;
	}

	public Integer getGeniusmiintrarank() {
		return geniusmiintrarank;
	}

	public void setGeniusmiintrarank(Integer geniusmiintrarank) {
		this.geniusmiintrarank = geniusmiintrarank;
	}

	public String getGeniusprefrontal() {
		return geniusprefrontal;
	}

	public void setGeniusprefrontal(String geniusprefrontal) {
		this.geniusprefrontal = geniusprefrontal;
	}

	public String getGeniusleadershippattern() {
		return geniusleadershippattern;
	}

	public void setGeniusleadershippattern(String geniusleadershippattern) {
		this.geniusleadershippattern = geniusleadershippattern;
	}

	public String getGeniusparent() {
		return geniusparent;
	}

	public void setGeniusparent(String geniusparent) {
		this.geniusparent = geniusparent;
	}

	public Integer getGeniuslanguagelistenrank() {
		return geniuslanguagelistenrank;
	}

	public void setGeniuslanguagelistenrank(Integer geniuslanguagelistenrank) {
		this.geniuslanguagelistenrank = geniuslanguagelistenrank;
	}

	public String getGeniusdos() {
		return geniusdos;
	}

	public void setGeniusdos(String geniusdos) {
		this.geniusdos = geniusdos;
	}

	public BigDecimal getGeniuseq() {
		return geniuseq;
	}

	public void setGeniuseq(BigDecimal geniuseq) {
		this.geniuseq = geniuseq;
	}

	public String getGeniusgrossmotorpattern() {
		return geniusgrossmotorpattern;
	}

	public void setGeniusgrossmotorpattern(String geniusgrossmotorpattern) {
		this.geniusgrossmotorpattern = geniusgrossmotorpattern;
	}

	public String getGeniusname() {
		return geniusname;
	}

	public void setGeniusname(String geniusname) {
		this.geniusname = geniusname;
	}

	public String getGeniusmilanguageper() {
		return geniusmilanguageper;
	}

	public void setGeniusmilanguageper(String geniusmilanguageper) {
		this.geniusmilanguageper = geniusmilanguageper;
	}

	public String getGeniusreferredby() {
		return geniusreferredby;
	}

	public void setGeniusreferredby(String geniusreferredby) {
		this.geniusreferredby = geniusreferredby;
	}

	public String getGeniusvisualpattern() {
		return geniusvisualpattern;
	}

	public void setGeniusvisualpattern(String geniusvisualpattern) {
		this.geniusvisualpattern = geniusvisualpattern;
	}

	public String getGeniustemporal() {
		return geniustemporal;
	}

	public void setGeniustemporal(String geniustemporal) {
		this.geniustemporal = geniustemporal;
	}

	public String getGeniusfinemotorpattern() {
		return geniusfinemotorpattern;
	}

	public void setGeniusfinemotorpattern(String geniusfinemotorpattern) {
		this.geniusfinemotorpattern = geniusfinemotorpattern;
	}

	public BigDecimal getGeniusvisuallearning() {
		return geniusvisuallearning;
	}

	public void setGeniusvisuallearning(BigDecimal geniusvisuallearning) {
		this.geniusvisuallearning = geniusvisuallearning;
	}

	public String getGeniusmikinasteticper() {
		return geniusmikinasteticper;
	}

	public void setGeniusmikinasteticper(String geniusmikinasteticper) {
		this.geniusmikinasteticper = geniusmikinasteticper;
	}

	public Integer getGeniusobservationrank() {
		return geniusobservationrank;
	}

	public void setGeniusobservationrank(Integer geniusobservationrank) {
		this.geniusobservationrank = geniusobservationrank;
	}

	public Integer getGeniusatdleft() {
		return geniusatdleft;
	}

	public void setGeniusatdleft(Integer geniusatdleft) {
		this.geniusatdleft = geniusatdleft;
	}

	public String getGeniusthinkright() {
		return geniusthinkright;
	}

	public void setGeniusthinkright(String geniusthinkright) {
		this.geniusthinkright = geniusthinkright;
	}

	public String getGeniusdob() {
		return geniusdob;
	}

	public void setGeniusdob(String geniusdob) {
		this.geniusdob = geniusdob;
	}

	public BigDecimal getGeniusrealistic() {
		return geniusrealistic;
	}

	public void setGeniusrealistic(BigDecimal geniusrealistic) {
		this.geniusrealistic = geniusrealistic;
	}

	public String getGeniusmotive() {
		return geniusmotive;
	}

	public void setGeniusmotive(String geniusmotive) {
		this.geniusmotive = geniusmotive;
	}

	public String getGeniusthinkleft() {
		return geniusthinkleft;
	}

	public void setGeniusthinkleft(String geniusthinkleft) {
		this.geniusthinkleft = geniusthinkleft;
	}

	public String getGeniusimaginepattern() {
		return geniusimaginepattern;
	}

	public void setGeniusimaginepattern(String geniusimaginepattern) {
		this.geniusimaginepattern = geniusimaginepattern;
	}

	public String getGeniuslanguagelistenpattern() {
		return geniuslanguagelistenpattern;
	}

	public void setGeniuslanguagelistenpattern(String geniuslanguagelistenpattern) {
		this.geniuslanguagelistenpattern = geniuslanguagelistenpattern;
	}

	public BigDecimal getGeniusartistic() {
		return geniusartistic;
	}

	public void setGeniusartistic(BigDecimal geniusartistic) {
		this.geniusartistic = geniusartistic;
	}

	public String getGeniusmusicpattern() {
		return geniusmusicpattern;
	}

	public void setGeniusmusicpattern(String geniusmusicpattern) {
		this.geniusmusicpattern = geniusmusicpattern;
	}

	public String getGeniusmathlogicpattern() {
		return geniusmathlogicpattern;
	}

	public void setGeniusmathlogicpattern(String geniusmathlogicpattern) {
		this.geniusmathlogicpattern = geniusmathlogicpattern;
	}

	public String getGeniusobservationpattern() {
		return geniusobservationpattern;
	}

	public void setGeniusobservationpattern(String geniusobservationpattern) {
		this.geniusobservationpattern = geniusobservationpattern;
	}

	public Integer getGeniusgrossmotorrank() {
		return geniusgrossmotorrank;
	}

	public void setGeniusgrossmotorrank(Integer geniusgrossmotorrank) {
		this.geniusgrossmotorrank = geniusgrossmotorrank;
	}

	public String getGeniusleftpercentage() {
		return geniusleftpercentage;
	}

	public void setGeniusleftpercentage(String geniusleftpercentage) {
		this.geniusleftpercentage = geniusleftpercentage;
	}

	public Integer getGeniusatdright() {
		return geniusatdright;
	}

	public void setGeniusatdright(Integer geniusatdright) {
		this.geniusatdright = geniusatdright;
	}

	public String getGeniusmiintraper() {
		return geniusmiintraper;
	}

	public void setGeniusmiintraper(String geniusmiintraper) {
		this.geniusmiintraper = geniusmiintraper;
	}

	public BigDecimal getGeniusconventional() {
		return geniusconventional;
	}

	public void setGeniusconventional(BigDecimal geniusconventional) {
		this.geniusconventional = geniusconventional;
	}

	public BigDecimal getGeniussocial() {
		return geniussocial;
	}

	public void setGeniussocial(BigDecimal geniussocial) {
		this.geniussocial = geniussocial;
	}

	public BigDecimal getGeniusiq() {
		return geniusiq;
	}

	public void setGeniusiq(BigDecimal geniusiq) {
		this.geniusiq = geniusiq;
	}

	public Integer getGeniusmanagementrank() {
		return geniusmanagementrank;
	}

	public void setGeniusmanagementrank(Integer geniusmanagementrank) {
		this.geniusmanagementrank = geniusmanagementrank;
	}

	public BigDecimal getGeniuscontact() {
		return geniuscontact;
	}

	public void setGeniuscontact(BigDecimal geniuscontact) {
		this.geniuscontact = geniuscontact;
	}

	public String getGeniusmimathper() {
		return geniusmimathper;
	}

	public void setGeniusmimathper(String geniusmimathper) {
		this.geniusmimathper = geniusmimathper;
	}

	public String getGeniuseintegratedstyle() {
		return geniuseintegratedstyle;
	}

	public void setGeniuseintegratedstyle(String geniuseintegratedstyle) {
		this.geniuseintegratedstyle = geniuseintegratedstyle;
	}

	public String getGeniusmispatialper() {
		return geniusmispatialper;
	}

	public void setGeniusmispatialper(String geniusmispatialper) {
		this.geniusmispatialper = geniusmispatialper;
	}

	public BigDecimal getGeniusaq() {
		return geniusaq;
	}

	public void setGeniusaq(BigDecimal geniusaq) {
		this.geniusaq = geniusaq;
	}

	public String getGeniusid() {
		return geniusid;
	}

	public void setGeniusid(String geniusid) {
		this.geniusid = geniusid;
	}

	private BigDecimal geniusconventional;

	private BigDecimal geniussocial;

	private BigDecimal geniusiq;

	private Integer geniusmanagementrank;

	private BigDecimal geniuscontact;

	private String geniusmimathper;

	private String geniuseintegratedstyle;

	private String geniusmispatialper;

	private BigDecimal geniusaq;

	private String geniusid;

	public String getMetdate() {
		return metdate;
	}

	public void setMetdate(String metdate) {
		this.metdate = metdate;
	}

	private String metdate;



}
