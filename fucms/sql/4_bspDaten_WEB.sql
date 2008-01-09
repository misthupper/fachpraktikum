/* Tabelle Webseitenvorlage */

INSERT INTO WEBSEITENVORLAGE (ID, NAME, DATEINAME)
VALUES (1, 'Fernuni', 'template.html');

INSERT INTO WEBSEITENVORLAGE (ID, NAME, DATEINAME)
VALUES (2, 'Startseite', 'templateStartseite.html');

/* Tabelle Medien */

INSERT INTO MEDIEN (ID, NAME, LINK)
VALUES (1, 'ER-Diagramm', 'downloads/er04.ppt');

INSERT INTO MEDIEN (ID, NAME, LINK)
VALUES (2, 'Fernuni-Logo', 'feulogo.gif');

/* Tabelle Version */

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (0, 0, 'root', 'Fernuni', 1, 1, 9, 2, 1);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (1, 0, 'Universität', 'Universität', 1, 1, 9, 2, 1);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (2, 0, 'Studium', 'Studium', 1, 1, 9, 2, 1);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (3, 0, 'Forschung', 'Forschung', 1, 1, 9, 5, 6);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (4, 0, 'Uni intern', 'Uni intern', 1, 1, 9, 3, 4);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (5, 4, 'OrganisationAnsprechpartner', 'Organisation/Ansprechpartner', 1, 1, 9, 4, 3);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (6, 5, 'Senatsbeauftragter', 'Senatsbeauftragter für behinderte und chronisch kranke Studierende', 1, 1, 9, 2, 1);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (7, 4, 'Finanzen und Beschaffung', 'Finanzen und Beschaffung', 1, 1, 9, 4, 3);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (8, 5, 'Gleichstellung', 'Gleichstellungsbeauftragter', 1, 1, 9, 4, 3);

INSERT INTO Version (id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (9, 4, 'Lehren und Forschen', 'Lehren und Forschen', 1, 1, 9, 2, 1);


/* Tabelle Inhalt */

INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('1', 'Text', '<!--anzeige der linklisten in den zusatzinformationen-->
     <ul>
      <li> Mehr zum Thema:
        <ul>
     <li><a href="http://www.fernuni-hagen.de/ZIFF/qbehind.htm">Senatsbeauftragter</a>
    </li>
        </ul>
      </li>
    </ul>');
    
INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('4', 'Text', 'Das ist ein Test für die Verlinkung von Mediendaten und hier ein Link auf die Datei <a href=FUCMS_PATH<!-- FUCMS.Medien.1.LINK -->> <!-- FUCMS.Medien.1.NAME --></a>. Hoffentlich funktioniert alles.');

INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('2', 'Text', '  <p>
  Zum Senatsbeauftragten f&uuml;r schwerbehinderte und chronisch kranke Studierende 
  hat der Senat der FernUniversit&auml;t in Hagen den Wissenschaftler <br /> <strong>
  <!-- FUCMS.Person.1.Name --></strong>, besch&auml;ftigt in der Stabsstelle f&uuml;r Qualit&auml;tssicherung und Evaluation, 
  berufen.</p> <p><strong> 
  <table summary="Angaben zur Email, Telefonnummer und Anschrift">
   <caption><strong>Senatsbeauftragter f&uuml;r behinderte und chronisch kranke Studierende</strong></caption> 
   <tbody> <tr> <th class="grau"><strong>Email</strong></th> <td>
   <a href="<!-- FUCMS.Person_Email.1.Email -->"><!-- FUCMS.Person_Email.1.Email --></a></td> </tr> <tr> <th class="grau">
   <strong>Telefon</strong></th> <td><!-- FUCMS.Person_Telefon.1.Telefon --></td> 
   </tr> <tr> <th class="grau"><strong>Gebäude</strong>
   </th> <td><!-- FUCMS.Gebaeude.1.Name -->, Raum <!-- FUCMS.Raum.1.Nummer --></td> </tr> <tr> <th class="grau">
   Anschrift</th> <td><!-- FUCMS.Gebaeude.1.Strasse --> <!-- FUCMS.Gebaeude.1.Hausnummer -->
   <br /> <!-- FUCMS.Gebaeude.1.postleitzahl --> <!-- FUCMS.Gebaeude.1.ort --></td> </tr> 
   </tbody> </table> </strong><br /> 
   In der Stabsstelle f&uuml;r Qualit&auml;tssicherung und Evaluation 
   ist Herr <!-- FUCMS.Person.1.Titel --> <!-- FUCMS.Person.1.Name --> erreichbar 
   unter<strong> <table summary="Angaben zur Email, Telefonnummer und Anschrift"> 
   <caption><strong></strong></caption> <tbody> <tr> 
   <th class="grau"><strong>Email</strong></th> <td>
   <a href="<!-- FUCMS.Person_Email.2.Email -->"><!-- FUCMS.Person_Email.2.Email -->
   </a></td> </tr> <tr> 
   <th class="grau"><strong>Telefon</strong></th> 
   <td><!-- FUCMS.Person_Telefon.2.Telefon --></td> </tr> <tr> 
   <th class="grau"><strong>Geb&auml;ude</strong></th> 
   <td><!-- FUCMS.Gebaeude.2.name -->, <!-- FUCMS.Raum.2.nummer --></td> 
   </tr> <tr> <th class="grau">Anschrift</th> <td><!-- FUCMS.Gebaeude.2.Strasse --> 
   <!-- FUCMS.Gebaeude.2.Hausnummer --><br /> <!-- FUCMS.Gebaeude.2.postleitzahl --> 
   Test<!-- FUCMS.Gebaeude.2.* -->Test</td> </tr> </tbody> </table> </strong><br /> </p>

   <div id="autor">
       <a href="mailto:Dez3.3@FernUni-Hagen.de; Fred.Froemming@fernuni-hagen.de">Dezernat 3.3</a> |
      18.09.2007 08:40
   </div>');


INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('3', 'Text', '<h1>Überschrift <!-- FUCMS.Person.2.Name --></h1>
<p>Das ist ein Testbeispiel für einen Inhalt</p>
<p>Unser Mitarbeiter heißt <!-- FUCMS.Person.1.Name --> und sitzt im Raum <!-- FUCMS.Person_Telefon.1.telefon -->.</p>');


INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('5', 'Text', '<a name="inhaltweiche"></a>

    <h1>Forschung an der FernUniversität in Hagen</h1>
  <div id="information">









   <div class="text-block">
   <img src="/imperia/md/images/forschung/forschung_lang.jpg" alt="Illustration" title="Illustration" id="bild2" />

   <div>

  <p>
Die FernUniversität in Hagen setzt auf Schwerpunkt- und Profilbildung in der Forschung. Durch interdisziplinäre und fakultätsübergreifende Zusammenarbeit werden Kompetenzen gebündelt und wettbewerbsfähige Forschungsschwerpunkte etabliert. 
</p>



   <p>
   Die grundlagen- und anwendungsorientierte Forschung ist zugleich Basis der Qualifizierung des wissenschaftlichen Nachwuchses.
</p>

  </div>

  </div><!--ende textblock-->
  <ul class="pfeil">

  <li>
    <a href="/forschung/schwerpunkte/index.shtml">Forschungsschwerpunkte</a>
  <br /> <p>Die FernUniversit&auml;t ist nationaler Kompetenzknoten f&uuml;r den Einsatz der Neuen Medien in Forschung und Lehre.</p>
  </li>

  <li>
    <a href="/forschung/einrichtungen/index.shtml">Forschungseinrichtungen</a>
  <br /> <p>Die Einrichtungen der FernUniversit&auml;t, ihre Forschungs- und An-Institute, ihr wissenschaftliches und gesellschaftliches Netzwerk pr&auml;gen das unverwechselbare Profil. </p>
  </li>

  <li>

    <a href="/forschung/wissenstransfer/index.shtml">Wissenstransfer</a>
  <br /> <p>Die FernUniversit&auml;t f&ouml;rdert den Wissens- und Technologietransfer sowie eine Kultur der Selbstst&auml;ndigkeit. </p>
  </li>

  <li>
    <a href="/forschung/berichte/index.shtml">Übersichten und Berichte</a>

  <br /> Universit&auml;t, Fakult&auml;ten, Institute und Lehrgebiete berichten kontinuierlich &uuml;ber laufende Forschungsaktivit&auml;ten, Projekte und Publikationen. 
  </li>

  </ul>');



INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) 
VALUES ('6', 'Text', '<ul>
      <li> Ansprechpersonen:
        <ul>

     <li><a href="http://www.fernuni-hagen.de/universitaet/profil/leitung/prorektor_forschung.shtml"><strong>Prorektor für Forschung</strong></a>
    </li>

     <li><a href="http://www.fernuni-hagen.de/arbeiten/organisation/beauftragte/rektoratsbeauftragte.shtml">Ombuds-/ Kontaktpersonen</a>
    </li>

        </ul>
      </li>

      <li> Links:
        <ul>

     <li><a href="http://www.fernuni-hagen.de/universitaet/fakultaeten.shtml">Fakultäten</a>
     </li>

     <li><a href="http://www.ub.fernuni-hagen.de/">Universitätsbibliothek</a>
     </li>

        </ul>
      </li>
      <li> Services:
        <ul>

     <li><a href="http://www.fernuni-hagen.de/arbeiten/lehren/forschungpraktisch/index.shtml"><strong>Forschungsförderung</strong></a>
     </li>

     <li><a href="http://www.fernuni-hagen.de/arbeiten/lehren/forschungpraktisch/wisspraxis.shtml">Wissenschaftsleitlinien</a>

     </li>

        </ul>
      </li>');

