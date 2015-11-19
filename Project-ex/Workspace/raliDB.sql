drop table if exists AFFECTATION;

drop table if exists AMORTISSMENT;

drop table if exists AVOIRDIPLOME;

drop table if exists CHARGESPATRONALES;

drop table if exists CONTENU;

drop table if exists CONTENUOPTIMISE;

drop table if exists COORDONNEES;

drop table if exists CV;

drop table if exists DIPLOME;

drop table if exists DOCUMENTATIONPHASE;

drop table if exists DOSSIERCAC;

drop table if exists DOSSIERCIR;

drop table if exists ENTREPRISE;

drop table if exists ETATPHASE;

drop table if exists GGPSYSTEME;

drop table if exists GGPS_AVOIR_PROFIL;

drop table if exists ICAC;

drop table if exists JOURSOUVRESORG;

drop table if exists JOURSSEMAINE;

drop table if exists ORGANISATION;

drop table if exists PHASEPLANIFICATION;

drop table if exists PHASERETROPLANIFICATION;

drop table if exists PHASE_P_AVOIR_ETAT;

drop table if exists PHASE_RP_AVOIR_ETAT;

drop table if exists PRESTATAIRE;

drop table if exists PRESTATION;

drop table if exists PROFIL;

drop table if exists PROJET;

drop table if exists RAPPORTACTIVITES;

drop table if exists RESSAMORT;

drop table if exists RESSCONSO;

drop table if exists RESSHUM;

drop table if exists RESSOURCE;

drop table if exists RETROAFFECTATION;

drop table if exists RH_AVOIR_PROFIL;

drop table if exists RH_AVOIR_STATUT;

drop table if exists SALAIREBRUT;

drop table if exists SIMULATIONCIR_SCIR;

drop table if exists STATUT;

drop table if exists TACHE;

drop table if exists TYPECOORDONNEES;

drop table if exists TYPEPRESTATAIRE;

/*==============================================================*/
/* Table: AFFECTATION                                           */
/*==============================================================*/
create table AFFECTATION
(
   AffectationId        int not null AUTO_INCREMENT,
   IDRESS               int not null,
   IDPHASE_P            int not null,
   DATEDEBUTAFFECT      date,
   DATEFINAFFECT        date,
   primary key (AffectationId)
);

/*==============================================================*/
/* Table: AMORTISSMENT                                          */
/*==============================================================*/
create table AMORTISSMENT
(
   IDAMORT              int not null AUTO_INCREMENT,
   IDRESSHUM            int,
   IDRESSAMORT          int,
   ANNEEAMORT           text,
   VALEURAMORT          decimal,
   primary key  (IDAMORT) 
);

/*==============================================================*/
/* Table: AVOIRDIPLOME                                          */
/*==============================================================*/
create table AVOIRDIPLOME
(
   AVOIRDIPLOMEID       int not null AUTO_INCREMENT,
   IDRESS               int not null,
   IDDIPLOME            int not null,
   DATEOBTENTION        date,
   primary key (AVOIRDIPLOMEID)
);

/*==============================================================*/
/* Table: CHARGESPATRONALES                                     */
/*==============================================================*/
create table CHARGESPATRONALES
(
   IDCP                 int not null 	AUTO_INCREMENT,
   IDRESS               int not null,
   CP_FROM              date,
   CP_TO                date,
   CP_valeur            decimal,
   primary key (IDCP)
);

/*==============================================================*/
/* Table: CONTENU                                               */
/*==============================================================*/
create table CONTENU
(
   ContenuId            int not null 	AUTO_INCREMENT,
   IDJOUR               int not null,
   IDRAPACTV            int not null,
   IDPHASE_P            int not null,
   IDTACHE              int not null,
   NBRHEURE             decimal,
   COMMENTAIRE          text,
   primary key (ContenuId)
);

/*==============================================================*/
/* Table: CONTENUOPTIMISE                                       */
/*==============================================================*/
create table CONTENUOPTIMISE
(
   ContenuoptimiseId    int not null 	AUTO_INCREMENT,
   IDJOUR               int not null ,
   IDRAPACTV            int not null,
   IDPHASE_RP           int not null,   
   IDTACHE              int not null,   
   NBRHEURESOPT         decimal,
   COMMENTAIREOPT       text,
   TAUXELIGIBILITE      decimal,
   primary key (ContenuoptimiseId)
);

/*==============================================================*/
/* Table: COORDONNEES                                           */
/*==============================================================*/
create table COORDONNEES
(
   IDCOORD              int not null 	AUTO_INCREMENT,

   IDTYPECOORD          int not null,
   IDRESS               int not null,
   VALCOORD             text,
   primary key (IDCOORD)
);

/*==============================================================*/
/* Table: CV                                                    */
/*==============================================================*/
create table CV
(
   IDCV                 int not null AUTO_INCREMENT,
   IDRESS               int not null,
   DATEMISEENLIGNE      date,
   LIENVERSSF_CV        text,
   primary key (IDCV)
);

/*==============================================================*/
/* Table: DIPLOME                                               */
/*==============================================================*/
create table DIPLOME
(
   IDDIPLOME            int not null AUTO_INCREMENT,
   LBLDIPLOME           text,
   primary key (IDDIPLOME)
);

/*==============================================================*/
/* Table: DOCUMENTATIONPHASE                                    */
/*==============================================================*/
create table DOCUMENTATIONPHASE
(
   IDDOC                int not null AUTO_INCREMENT,
   IDPHASE_RP           int,
   IDPHASE_P            int,
   LBLDOC               text,
   DESCDOC              text,
   DATEMISEENLIGNEDOC   date,
   LIENVERS_SF_DOC      text,
   primary key (IDDOC)
);


/*==============================================================*/
/* Table: DOSSIERCAC                                            */
/*==============================================================*/
create table DOSSIERCAC
(
   dossierCacId         int not null AUTO_INCREMENT,
   IDICAC               int not null,
   IDGGPS               int not null,
   IDENTREPRISE         int not null,
   ANNEEDOSSCAC         text,
   primary key ( dossierCacId)
);

/*==============================================================*/
/* Table: DOSSIERCIR                                            */
/*==============================================================*/
create table DOSSIERCIR
(
   DossierCirId         int not null AUTO_INCREMENT,
   IDGGPS               int not null,
   IDENTREPRISE         int not null,
   IDSCIR               int not null,
   ANNEEDOSSCIR         text,
   primary key (DossierCirId)
);

/*==============================================================*/
/* Table: ENTREPRISE                                            */
/*==============================================================*/
create table ENTREPRISE
(
   IDENTREPRISE         int not null ,
   NOMENTREPRISE        text,
   LOGINGENTREPRISE     text,
   ISJEI                bool,
   NBRJOURSTRAV         int,
   PREMIERANNECIR       text,
   HASACCESSTOICAC      bool,
   HASACESSTORAOPTIM    bool,
   HASACESSTORETROPLANIF bool,
   primary key (IDENTREPRISE)
);

/*==============================================================*/
/* Table: ETATPHASE                                             */
/*==============================================================*/
create table ETATPHASE
(
   IDETATPHASE          int not null AUTO_INCREMENT,
   LBLETATPHASE         text,
   primary key (IDETATPHASE)
);

/*==============================================================*/
/* Table: GGPSYSTEME                                            */
/*==============================================================*/
create table GGPSYSTEME
(
   IDGGPS               int not null AUTO_INCREMENT,
   ACROGGPS             text,
   NOMGGPS              text,
   PRENOMGGPS           text,
   EMAILGGPS            text,
   MDPGGPS              text,
   primary key (IDGGPS)
);

/*==============================================================*/
/* Table: GGPS_AVOIR_PROFIL                                     */
/*==============================================================*/
create table GGPS_AVOIR_PROFIL
(
   GGPSAVOIRPROFILID    int not null,
   IDGGPS               int not null,
   IDPROFIL             int not null,
   primary key (GGPSAVOIRPROFILID)
);

/*==============================================================*/
/* Table: ICAC                                                  */
/*==============================================================*/
create table ICAC
(
   IDICAC               int not null AUTO_INCREMENT,
   DATE_ICAC            date,
   LIEN_SF_DEPRD        text,
   LIEN_SF_DEPRI        text,
   primary key (IDICAC)
);


/*==============================================================*/
/* Table: JOURSOUVRESORG                                        */
/*==============================================================*/
create table JOURSOUVRESORG
(
   JOURSOUVRESORGID     int not null AUTO_INCREMENT,
   IDJOUR               int not null,
   IDORG                int not null,
   ISWORKINGDAYORG      bool,
   primary key (JOURSOUVRESORGID)
);

/*==============================================================*/
/* Table: JOURSSEMAINE                                          */
/*==============================================================*/
create table JOURSSEMAINE
(
   IDJOUR               int not null AUTO_INCREMENT,
   NOMJOUR              text,
   primary key (IDJOUR)
);

/*==============================================================*/
/* Table: ORGANISATION                                          */
/*==============================================================*/
create table ORGANISATION
(
   IDORG                int not null ,
   IDORG_PERE            int,
   IDENTREPRISE         int,
   LBLORG               text,
   PROFONDEURORG        int,
   ISENTREPRISE         bool,
   ISINTEGFISCAL        bool,
   primary key (IDORG)
);

/*==============================================================*/
/* Table: PHASEPLANIFICATION                                    */
/*==============================================================*/
create table PHASEPLANIFICATION
(
   IDPHASE_P            int not null AUTO_INCREMENT,
   IDPHASE_P_PERE        int,
   IDPROJET             int,
   LBLPHASE_P           text,
   DESCPHASE_P          text,
   PROFONDPHASE_P       int,
   DATEDEBPHASE_P       date,
   DATEFINPHASE_P       date,
   primary key (IDPHASE_P)
);

/*==============================================================*/
/* Table: PHASERETROPLANIFICATION                               */
/*==============================================================*/
create table PHASERETROPLANIFICATION
(
   IDPHASE_RP           int not null AUTO_INCREMENT,
   IDPHASE_RP_PERE      int,
   IDPROJET             int,
   LBLPHASE_RP          text,
   DESCPHASE_RP         text,
   PROFONDPHASE_RP      int,
   DATEDEBPHASE_RP      date,
   DATEFINPHASE_RP      date,
   primary key (IDPHASE_RP)
);

/*==============================================================*/
/* Table: PHASE_P_AVOIR_ETAT                                    */
/*==============================================================*/
create table PHASE_P_AVOIR_ETAT
(
   PhasePAvoirEtatId    int not null AUTO_INCREMENT,
   IDETATPHASE          int not null,
   IDPHASE_P            int not null,
   P_AVOIR_ETAT_FROM    date,
   P_AVOIR_ETAT_TO      date,
   primary key (PhasePAvoirEtatId)
);

/*==============================================================*/
/* Table: PHASE_RP_AVOIR_ETAT                                   */
/*==============================================================*/
create table PHASE_RP_AVOIR_ETAT
(
   PhaseRpAvoirEtatId   int not null AUTO_INCREMENT,
   IDPHASE_RP           int not null,
   IDETATPHASE          int not null,
   RP_AVOIR_ETAT_FROM   date,
   RP_AVOIR_ETAT_TO     date,
   primary key (PhaseRpAvoirEtatId)
);

/*==============================================================*/
/* Table: PRESTATAIRE                                           */
/*==============================================================*/
create table PRESTATAIRE
(
   IDPRESTATAIRE        int not null AUTO_INCREMENT,
   LBLPRESTATAIRE       text,
   primary key (IDPRESTATAIRE)
);

/*==============================================================*/
/* Table: PRESTATION                                            */
/*==============================================================*/
create table PRESTATION
(
   IDPRESTATION         int not null AUTO_INCREMENT,
   IDPROJET             int not null,
   IDPRESTATAIRE        int not null,
   IDTYPEPREST          int not null,
   IDORG                int,
   DATEPRESTATION       date,
   DEVISPRESTATION      decimal,
   FACTPRESTATION       decimal,
   DESCPRESTATION       text,
   TAUXELIGIBILTE       decimal,
   primary key (IDPRESTATION)
);

/*==============================================================*/
/* Table: PROFIL                                                */
/*==============================================================*/
create table PROFIL
(
   IDPROFIL             int not null,
   LBLPROFIL            text,
   primary key (IDPROFIL)
);

/*==============================================================*/
/* Table: PROJET                                                */
/*==============================================================*/
create table PROJET
(
   IDPROJET             int not null AUTO_INCREMENT,
   ID_RESS_CDP          int not null,
   LBLPROJET            text,
   DESCPROJET           text,
   DATEDEBPROJET        date,
   DATEFINPROJ          date,
   primary key (IDPROJET)
);

/*==============================================================*/
/* Table: RAPPORTACTIVITES                                      */
/*==============================================================*/
create table RAPPORTACTIVITES
(
   IDRAPACTV            int not null AUTO_INCREMENT,
   IDRESS               int not null,
   ANNEE          		text,
   NUMSEMAINE           text,
   primary key (IDRAPACTV)
);

/*==============================================================*/
/* Table: RESSAMORT                                             */
/*==============================================================*/
create table RESSAMORT
(
   IDRESS               int not null AUTO_INCREMENT,
   DESIGNATIONRA        text,
   DATEACQUISITIONRA    date,
   VALACQUISITIONRA     decimal,
   primary key (IDRESS)
);

/*==============================================================*/
/* Table: RESSCONSO                                             */
/*==============================================================*/
create table RESSCONSO
(
   IDRESS               int not null AUTO_INCREMENT,
   DESIGNATIONRC        text,
   DATEACQUISITIONRC    date,
   QUANTITEACQUISERC    decimal,
   PRIXUNITAIRE         decimal,
   primary key  (IDRESS) 
);

/*==============================================================*/
/* Table: RESSHUM                                               */
/*==============================================================*/
create table RESSHUM
(
   IDRESS               int not null AUTO_INCREMENT,
   NOMRH                text,
   PRENOMRH             text,
   ACRONYME             text,
   DATEEMBAUCHE         date,
   ISFIRSTCDI           bool,
   LOGING               text,
   EMAILRH              text,
   PWDRH                text,
   primary key   (IDRESS)
);


/*==============================================================*/
/* Table: RESSOURCE                                             */
/*==============================================================*/
create table RESSOURCE
(
   IDRESS               int not null auto_increment,
   IDORG                int not null,
   primary key (IDRESS)
);

/*==============================================================*/
/* Table: RETROAFFECTATION                                      */
/*==============================================================*/
create table RETROAFFECTATION
(
   RetroaffectationId   int not null auto_increment,
   IDPHASE_RP           int not null,
   IDRESS               int not null,
   DATEDEBREAFFECT      date,
   DATEFINREAFFECT      date,
   primary key (RetroaffectationId)
);

/*==============================================================*/
/* Table: RH_AVOIR_PROFIL                                       */
/*==============================================================*/
create table RH_AVOIR_PROFIL
(
   RHAVOIRPROFILID      int not null,
   IDRESS               int not null,
   IDPROFIL             int not null,   
   primary key (RHAVOIRPROFILID)
);

/*==============================================================*/
/* Table: RH_AVOIR_STATUT                                       */
/*==============================================================*/
create table RH_AVOIR_STATUT
(
   avoirStatutId        int not null auto_increment,
   IDRESS               int not null,
   IDSTATUT             int not null,
   RH_AVOIR_STATUT_FROM date,
   RH_AVOIR_STATUT_TO   date,
   primary key (avoirStatutId)
);

/*==============================================================*/
/* Table: SALAIREBRUT                                           */
/*==============================================================*/
create table SALAIREBRUT
(
   IDSB                 int not null,
   IDRESS               int not null,
   SB_FROM              date,
   SB_TO                date,
   SB_valeur            decimal,
   primary key (IDSB)
);

/*==============================================================*/
/* Table: SIMULATIONCIR_SCIR                                    */
/*==============================================================*/
create table SIMULATIONCIR_SCIR
(
   IDSCIR               int not null,
   DATESCIR             date,
   DOTAMORT             decimal,
   DEPPERSCHERTECH      decimal,
   DEPPERSJD            decimal,
   DEPFCT               decimal,
   PRISEMAINTBREVT      decimal,
   DEPDEFBRVT           decimal,
   DOTAMORTBRVT         decimal,
   MOITSALCHRG          decimal,
   AUTRDEPNORM          decimal,
   MOITDEP              decimal,
   PRESTPUB             decimal,
   PRESTPRIVAGR         decimal,
   VEILLTECHNO          decimal,
   TEXTIL               decimal,
   SOUSTRAIT            decimal,
   primary key (IDSCIR)
);

/*==============================================================*/
/* Table: STATUT                                                */
/*==============================================================*/
create table STATUT
(
   IDSTATUT             int not null AUTO_INCREMENT,
   LBLSTATUT            text,
   primary key (IDSTATUT)
);

/*==============================================================*/
/* Table: TACHES_PROJETS                                                */
/*==============================================================*/
create table TACHE
(
   IDTACHE             int not null AUTO_INCREMENT,
   LBLTACHE            text,
   primary key (IDTACHE)
);


/*==============================================================*/
/* Table: TYPECOORDONNEES                                       */
/*==============================================================*/
create table TYPECOORDONNEES
(
   IDTYPECOORD          int not null AUTO_INCREMENT,
   LBLTYPECOORD         text,
   primary key (IDTYPECOORD)
);

/*==============================================================*/
/* Table: TYPEPRESTATAIRE                                       */
/*==============================================================*/
create table TYPEPRESTATAIRE
(
   IDTYPEPREST          int not null AUTO_INCREMENT,
   LBLTYPEPREST         text,
   primary key (IDTYPEPREST)
);

alter table AFFECTATION add constraint FK_AFFECTATION foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table AFFECTATION add constraint FK_AFFECTATION2 foreign key (IDPHASE_P)
      references PHASEPLANIFICATION (IDPHASE_P) on delete restrict on update restrict;

alter table AMORTISSMENT add constraint FK_AMORTIR_RA foreign key (IDRESSAMORT)
      references RESSAMORT (IDRESS) on delete restrict on update restrict;

alter table AMORTISSMENT add constraint FK_AMORTIR_RH foreign key (IDRESSHUM)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table AVOIRDIPLOME add constraint FK_AVOIRDIPLOME foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table AVOIRDIPLOME add constraint FK_AVOIRDIPLOME2 foreign key (IDDIPLOME)
      references DIPLOME (IDDIPLOME) on delete restrict on update restrict;

alter table CHARGESPATRONALES add constraint FK_RH_AVOIR_CP foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table CONTENU add constraint FK_CONTENU foreign key (IDJOUR)
      references JOURSSEMAINE (IDJOUR) on delete restrict on update restrict;

alter table CONTENU add constraint FK_CONTENU2 foreign key (IDRAPACTV)
      references RAPPORTACTIVITES (IDRAPACTV) on delete restrict on update restrict;

alter table CONTENU add constraint FK_CONTENU3 foreign key (IDPHASE_P)
      references PHASEPLANIFICATION (IDPHASE_P) on delete restrict on update restrict;

alter table CONTENU add constraint FK_CONTENU4 foreign key (IDTACHE)
      references TACHE (IDTACHE) on delete restrict on update restrict;

alter table CONTENUOPTIMISE add constraint FK_CONTENUOPTIMISE foreign key (IDJOUR)
      references JOURSSEMAINE (IDJOUR) on delete restrict on update restrict;

alter table CONTENUOPTIMISE add constraint FK_CONTENUOPTIMISE2 foreign key (IDPHASE_RP)
      references PHASERETROPLANIFICATION (IDPHASE_RP) on delete restrict on update restrict;

alter table CONTENUOPTIMISE add constraint FK_CONTENUOPTIMISE3 foreign key (IDRAPACTV)
      references RAPPORTACTIVITES (IDRAPACTV) on delete restrict on update restrict;

alter table CONTENUOPTIMISE add constraint FK_CONTENU4 foreign key (IDTACHE)
      references TACHE (IDTACHE) on delete restrict on update restrict;

alter table COORDONNEES add constraint FK_AVOIRCOORDONNEES foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table COORDONNEES add constraint FK_AVOIRTYPECOORD foreign key (IDTYPECOORD)
      references TYPECOORDONNEES (IDTYPECOORD) on delete restrict on update restrict;

alter table CV add constraint FK_AVOIRCV foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table DOCUMENTATIONPHASE add constraint FK_PHASE_P_AD foreign key (IDPHASE_P)
      references PHASEPLANIFICATION (IDPHASE_P) on delete restrict on update restrict;

alter table DOCUMENTATIONPHASE add constraint FK_PHASE_RP_AD foreign key (IDPHASE_RP)
      references PHASERETROPLANIFICATION (IDPHASE_RP) on delete restrict on update restrict;

alter table PRESTATION add constraint FK_DONNERPRESTATION2 foreign key (IDPRESTATAIRE)
      references PRESTATAIRE (IDPRESTATAIRE) on delete restrict on update restrict;

alter table PRESTATION add constraint FK_DONNERPRESTATION3 foreign key (IDTYPEPREST)
      references TYPEPRESTATAIRE (IDTYPEPREST) on delete restrict on update restrict;

alter table DOSSIERCAC add constraint FK_DOSSIERCAC foreign key (IDICAC)
      references ICAC (IDICAC) on delete restrict on update restrict;

alter table DOSSIERCAC add constraint FK_DOSSIERCAC2 foreign key (IDGGPS)
      references GGPSYSTEME (IDGGPS) on delete restrict on update restrict;

alter table DOSSIERCAC add constraint FK_DOSSIERCAC3 foreign key (IDENTREPRISE)
      references ENTREPRISE (IDENTREPRISE) on delete restrict on update restrict;

alter table DOSSIERCIR add constraint FK_DOSSIERCIR foreign key (IDGGPS)
      references GGPSYSTEME (IDGGPS) on delete restrict on update restrict;

alter table DOSSIERCIR add constraint FK_DOSSIERCIR2 foreign key (IDENTREPRISE)
      references ENTREPRISE (IDENTREPRISE) on delete restrict on update restrict;

alter table DOSSIERCIR add constraint FK_DOSSIERCIR3 foreign key (IDSCIR)
      references SIMULATIONCIR_SCIR (IDSCIR) on delete restrict on update restrict;

alter table JOURSOUVRESORG add constraint FK_JOURSOUVRESORG foreign key (IDJOUR)
      references JOURSSEMAINE (IDJOUR) on delete restrict on update restrict;

alter table JOURSOUVRESORG add constraint FK_JOURSOUVRESORG2 foreign key (IDORG)
      references ORGANISATION (IDORG) on delete restrict on update restrict;

alter table ORGANISATION add constraint FK_ETRERACINE foreign key (IDENTREPRISE)
      references ENTREPRISE (IDENTREPRISE) on delete restrict on update restrict;

alter table ORGANISATION add constraint FK_ETRESOUSORG foreign key (IDORG_PERE)
      references ORGANISATION (IDORG) on delete restrict on update restrict;

alter table PHASEPLANIFICATION add constraint FK_ETRESOUSPHASE_P foreign key (IDPHASE_P_PERE)
      references PHASEPLANIFICATION (IDPHASE_P) on delete restrict on update restrict;

alter table PHASEPLANIFICATION add constraint FK_PROJETPLANIFIE foreign key (IDPROJET)
      references PROJET (IDPROJET) on delete restrict on update restrict;

alter table PHASERETROPLANIFICATION add constraint FK_ETRESOUSPHASE_RP foreign key (IDPHASE_RP_PERE)
      references PHASERETROPLANIFICATION (IDPHASE_RP) on delete restrict on update restrict;

alter table PHASERETROPLANIFICATION add constraint FK_PROJETRETROPLANIFIE foreign key (IDPROJET)
      references PROJET (IDPROJET) on delete restrict on update restrict;

alter table PHASE_P_AVOIR_ETAT add constraint FK_PHASE_P_AVOIR_ETAT foreign key (IDETATPHASE)
      references ETATPHASE (IDETATPHASE) on delete restrict on update restrict;

alter table PHASE_P_AVOIR_ETAT add constraint FK_PHASE_P_AVOIR_ETAT2 foreign key (IDPHASE_P)
      references PHASEPLANIFICATION (IDPHASE_P) on delete restrict on update restrict;

alter table PHASE_RP_AVOIR_ETAT add constraint FK_PHASE_RP_AVOIR_ETAT foreign key (IDPHASE_RP)
      references PHASERETROPLANIFICATION (IDPHASE_RP) on delete restrict on update restrict;

alter table PHASE_RP_AVOIR_ETAT add constraint FK_PHASE_RP_AVOIR_ETAT2 foreign key (IDETATPHASE)
      references ETATPHASE (IDETATPHASE) on delete restrict on update restrict;

alter table PRESTATION add constraint FK_ORG_AVOIR_PRESTATION foreign key (IDORG)
      references ORGANISATION (IDORG) on delete restrict on update restrict;

alter table PRESTATION add constraint FK_PREST_CONCERNE_PROJET foreign key (IDPROJET)
      references PROJET (IDPROJET) on delete restrict on update restrict;

alter table PROJET add constraint FK_ETRECHEFPROJET foreign key (ID_RESS_CDP)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RAPPORTACTIVITES add constraint FK_AVOIRRAPACTV foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RESSAMORT add constraint FK_INHERITANCE_1 foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RESSOURCE add constraint FK_ETRERESSOURCE foreign key (IDORG)
      references ORGANISATION (IDORG) on delete restrict on update restrict;

alter table RESSCONSO add constraint FK_HERITAGERC foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RESSHUM add constraint FK_INHERITANCE_2 foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RETROAFFECTATION add constraint FK_RETROAFFECTATION foreign key (IDPHASE_RP)
      references PHASERETROPLANIFICATION (IDPHASE_RP) on delete restrict on update restrict;

alter table RETROAFFECTATION add constraint FK_RETROAFFECTATION2 foreign key (IDRESS)
      references RESSOURCE (IDRESS) on delete restrict on update restrict;

alter table RH_AVOIR_STATUT add constraint FK_RH_AVOIR_STATUT foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;

alter table RH_AVOIR_STATUT add constraint FK_RH_AVOIR_STATUT2 foreign key (IDSTATUT)
      references STATUT (IDSTATUT) on delete restrict on update restrict;

alter table SALAIREBRUT add constraint FK_RH_AVOIR_SB foreign key (IDRESS)
      references RESSHUM (IDRESS) on delete restrict on update restrict;
