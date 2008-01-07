/* Tabelle Version */

INSERT INTO Version (id, vaterseiteID, path, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (0, 0, 'root', 2, 1);

INSERT INTO Version (id, vaterseiteID, path, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (1, 0, 'zwei', 2, 1);

INSERT INTO Version (id, vaterseiteID, path, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (2, 0, 'drei', 2, 1);

INSERT INTO Version (id, vaterseiteID, path, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (3, 1, 'vier', 2, 1);

INSERT INTO Version (id, vaterseiteID, path, hauptseiteninhaltID, seitenleisteInhaltID)
VALUES (9, 0, 'Senatsbeauftragter', 2, 1);


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
VALUES ('4', 'Text', 'Test');

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


