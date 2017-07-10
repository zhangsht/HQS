using System.Collections.Generic;
using System;

namespace BLL.Network {
    public class NetworkWorker
    {
        string ip= "http://localhost:8080";
        public string login(INITIALIZE initialize)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip+ "/iQueue/user";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", initialize.opcode));
                values.Add(new KeyValuePair<string, string>("username", initialize.user.username));
                values.Add(new KeyValuePair<string, string>("password", initialize.user.password));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string initQueueInfo(INITQUEUEINFO initQueueInfo_) {
            try {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/officeQueue";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", initQueueInfo_.opcode));
                values.Add(new KeyValuePair<string, string>("officeId", "guke8765"));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            } catch {
                return "unable to connect server:timeout(3)";
            }

        }
        public string initclinic(INITCLINIC initclinic_)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/initClinic";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", initclinic_.opcode));
                values.Add(new KeyValuePair<string, string>("cid", initclinic_.cid));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string getClinicList()
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/getClinicList";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", "getClinicList"));
                values.Add(new KeyValuePair<string, string>("officeId", "guke8765"));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string getClinicDetail(string clinicId)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/clinicDetail";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", "clinicDetail"));
                values.Add(new KeyValuePair<string, string>("clinicId", clinicId));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string add_patient(ADD_PATIENT add_patient_)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/add";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", add_patient_.opcode));
                values.Add(new KeyValuePair<string, string>("pId", add_patient_.info.patientID));
                values.Add(new KeyValuePair<string, string>("name", add_patient_.info.name));
                values.Add(new KeyValuePair<string, string>("cardNumber", add_patient_.info.cardNum));
                values.Add(new KeyValuePair<string, string>("sex", add_patient_.info.sex));
                values.Add(new KeyValuePair<string, string>("age", add_patient_.info.age));
                values.Add(new KeyValuePair<string, string>("registerTime", add_patient_.info.registration_time));
                values.Add(new KeyValuePair<string, string>("arriveTime", add_patient_.info.visting_time));

                values.Add(new KeyValuePair<string, string>("height", add_patient_.info.height)); 
                values.Add(new KeyValuePair<string, string>("weight", add_patient_.info.weight));
                values.Add(new KeyValuePair<string, string>("temperature", add_patient_.info.temperature));
                values.Add(new KeyValuePair<string, string>("respiration", add_patient_.info.respiration));
                values.Add(new KeyValuePair<string, string>("pulse", add_patient_.info.pulse));
                values.Add(new KeyValuePair<string, string>("bloodPressure", add_patient_.info.blood_pressure));
                values.Add(new KeyValuePair<string, string>("description", add_patient_.info.disease_description));
                values.Add(new KeyValuePair<string, string>("bloodSugar", add_patient_.info.blood_sugar));

                var content = new FormUrlEncodedContent(values);
                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;
                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string next(DOCTOR_SAY_NEXT next)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/change";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", next.opcode));
                values.Add(new KeyValuePair<string, string>("newQueueId", next.newQueueId));
                values.Add(new KeyValuePair<string, string>("pId", next.pId));

                var content = new FormUrlEncodedContent(values);
                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;
                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string initPatientInfo(INITPATIENTINFO init)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/initPatientInfo";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", init.opcode));

                var content = new FormUrlEncodedContent(values);
                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;
                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string initDoctorInfo(INITDOCTORINFO initDoctorInfo_)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/officeQueue";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", initDoctorInfo_.opcode));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string triage(TRIAGE_OFFICE triage_office_)
        {
            try
            {
                HttpClient httpClient = new HttpClient();
                httpClient.MaxResponseContentBufferSize = 256000;
                httpClient.Timeout = TimeSpan.FromSeconds(3);
                String url = ip + "/iQueue/choiceDoctor";

                var values = new List<KeyValuePair<string, string>>();
                values.Add(new KeyValuePair<string, string>("opcode", triage_office_.opcode));
                values.Add(new KeyValuePair<string, string>("pId", triage_office_.pId));
                values.Add(new KeyValuePair<string, string>("clinicId", triage_office_.clinicId));
                var content = new FormUrlEncodedContent(values);

                HttpResponseMessage response = new HttpResponseMessage();
                response = httpClient.PostAsync(new Uri(url), content).Result;

                String result = response.Content.ReadAsStringAsync().Result;
                return result;
            }
            catch
            {
                return "unable to connect server:timeout(3)";
            }

        }
        public string Send_action(object pb)
        {
            if (pb is User)
            {
                INITIALIZE user_info = new INITIALIZE();
                user_info.user = (User)pb;
                return login(user_info);
            }
            else if (pb is PatientInfo)
            {
                ADD_PATIENT ap = new ADD_PATIENT();
                ap.info = (PatientInfo)pb;
                return add_patient(ap);
            }
            else if (pb is ToQueue)
            {
                DOCTOR_SAY_NEXT ap = new DOCTOR_SAY_NEXT();
                ap.pId = ((ToQueue)pb).pId;
                ap.newQueueId = ((ToQueue)pb).queueId;
                return next(ap);
            }
            else if (pb is TriageOffice)
            {
                TRIAGE_OFFICE ap = new TRIAGE_OFFICE();
                ap.pId = ((TriageOffice)pb).pId;
                ap.clinicId = ((TriageOffice)pb).clinicId;
                return triage(ap);
            }
            else return "invalid sending object";
        }
        
    }
}