using System.Windows;
using System.Windows.Data;
using BLL.Network;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Collections.ObjectModel;
using System;
using System.Collections.Generic;
using System.Threading;

namespace iQueue
{
    /// <summary>
    /// patient_watch.xaml 的交互逻辑
    /// </summary>
    public partial class patient_watch : Window
    {

        CollectionViewSource first_view = new CollectionViewSource();
        CollectionViewSource second_view = new CollectionViewSource();
        CollectionViewSource trigae_view = new CollectionViewSource();
        CollectionViewSource waiting_view = new CollectionViewSource();
        CollectionViewSource watching_view = new CollectionViewSource();
        CollectionViewSource finish_view = new CollectionViewSource();
        CollectionViewSource doctor_view = new CollectionViewSource();
        CollectionViewSource patient_view = new CollectionViewSource();
        //用来保存所有的病人信息
        ObservableCollection<PatientInfo> patients = new ObservableCollection<PatientInfo>();
        ObservableCollection<row> first = new ObservableCollection<row>();
        ObservableCollection<row> second = new ObservableCollection<row>();
        ObservableCollection<row> triage = new ObservableCollection<row>();
        ObservableCollection<row> waiting = new ObservableCollection<row>();
        ObservableCollection<row> watching = new ObservableCollection<row>();
        ObservableCollection<row> finish = new ObservableCollection<row>();
        ObservableCollection<Doctor_List_row> doctors = new ObservableCollection<Doctor_List_row>();
        //
        string firTreatId;
        string secTreatId;
        string dispatchTreatId;
        string oId;
        //doctor&clinic
        string dId;
        string doctorName;
        string cId;
        string profile;
        string startTime, endTime;
        string preTreatId, inTreatId, afterTreatId;

        public patient_watch()
        {
            bool success = false;

            while (success == false)
            {
                try
                {
                    //初始化的时候获取所有病人信息
                    InitializeComponent();
                    InitQueueInfo();
                    getClinicList();
                    Init_Patient_Info();
                    //InitDoctorInfo();
                    success = true;
                }
                catch
                {
                    MessageBox.Show("无法初始化，将在三秒后重启");
                    Thread.Sleep(3000);
                }
            }

        }
        public void refresh()
        {
            //刷新
            InitializeComponent();
            InitQueueInfo();
            Init_Patient_Info();
            //InitDoctorInfo();
        }
        void view_Filter(object sender, FilterEventArgs e)
        {
            int index = patients.IndexOf((PatientInfo)e.Item);
        }

        private void Add_Click(object sender, RoutedEventArgs e)
        {
            add_patient ap = new add_patient();
            ap.Show();
        }

        //挂号
        private void Register(object sender, RoutedEventArgs e)
        {
            if (PatientView.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = patients[PatientView.SelectedIndex].patientID;
                    to.queueId = firTreatId;
                    string result = nw.Send_action(to);
                    InitQueueInfo();
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }
            }
        }
        //报道
        private void CheckIn(object sender, RoutedEventArgs e)
        {
            if (First_queue.SelectedIndex == -1)
            {
                MessageBox.Show("请选择病人");
            }
            else
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = first[First_queue.SelectedIndex].pId;
                    to.queueId = dispatchTreatId;
                    string result = nw.Send_action(to);
                    InitQueueInfo();
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }
        }
        private void Triage(object sender, RoutedEventArgs e)
        {
            if (TriageOfficeList.SelectedIndex != -1 && Triage_queue.SelectedIndex != -1)
            {
                NetworkWorker nw = new NetworkWorker();
                TriageOffice to = new TriageOffice();
                to.clinicId = (string)TriageOfficeList.SelectedItem.ToString();
                to.pId = triage[Triage_queue.SelectedIndex].pId;

                string result = nw.Send_action(to);
                InitQueueInfo();
            }
            else
            {
                MessageBox.Show("请选择诊室和病人");
            }
        }


        private void Add_office(object sender, RoutedEventArgs e)
        {
            add_office ao = new add_office();
            ao.Show();
        }
        private void InitQueueInfo()
        {
            INITQUEUEINFO initQueueInfo_ = new INITQUEUEINFO();
            first.Clear();
            second.Clear();
            triage.Clear();
            try
            {
                NetworkWorker nw = new NetworkWorker();
                string result = nw.initQueueInfo(initQueueInfo_);
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                //截取病人信息

                try
                {
                    JObject officeInfo = (JObject)(json["officeInfo"]);
                    firTreatId = officeInfo["firTreatId"].ToString();
                    secTreatId = officeInfo["secTreatId"].ToString();
                    dispatchTreatId = officeInfo["dispatchTreatId"].ToString();
                    oId = officeInfo["oId"].ToString();
                }
                catch
                {
                    MessageBox.Show("初始科室信息");
                }
                try
                {
                    int position = 0;
                    //获取首诊队列信息
                    JArray array = (JArray)(json["firstQueue"]);

                    foreach (var jObject in array)
                    {
                        first.Add(new row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>(),
                            pId = jObject["pId"].ToObject<string>()
                        });
                    }
                    first_view.Source = first;
                    First_queue.DataContext = first_view;
                }
                catch
                {
                    MessageBox.Show("无法解析首诊队列数据");
                }
                try
                {
                    int position = 0;
                    //获取二诊队列信息
                    JArray array = (JArray)(json["secondQueue"]);
                    foreach (var jObject in array)
                    {
                        second.Add(new row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>(),
                            pId = jObject["pId"].ToObject<string>()
                        });
                    }
                    second_view.Source = second;
                    Second_queue.DataContext = second_view;
                }
                catch
                {
                    MessageBox.Show("无法解析二诊队列数据");
                }

                try
                {
                    int position = 0;
                    //获取转诊队列信息
                    JArray array = (JArray)(json["triageQueue"]);
                    foreach (var jObject in array)
                    {
                        triage.Add(new row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>(),
                            pId = jObject["pId"].ToObject<string>()
                        });
                    }
                    trigae_view.Source = triage;
                    Triage_queue.DataContext = trigae_view;
                }
                catch
                {
                    MessageBox.Show("无法解析转诊队列数据");
                }
            }
            catch
            {
                MessageBox.Show("服务器错误");
            }
        }
        private void getClinicList()
        {
            TriageOfficeList.Items.Clear();
            SelectedOfficeList.Items.Clear();
            NetworkWorker nw = new NetworkWorker();
            string result = nw.getClinicList();
            JObject json = (JObject)JsonConvert.DeserializeObject(result);
            JArray array = (JArray)(json["clinicList"]);
            foreach (var jObject in array)
            {
                TriageOfficeList.Items.Add(jObject["cid"]);
                SelectedOfficeList.Items.Add(jObject["cid"]);
            }
        }

        private void Init_Patient_Info()
        {
            patients.Clear();
            try
            {
                INITPATIENTINFO initPatientInfo_ = new INITPATIENTINFO();
                NetworkWorker nw = new NetworkWorker();
                string result = nw.initPatientInfo(initPatientInfo_);
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                try
                {
                    JArray patientInfo = json["patientInfo"].ToObject<JArray>();
                    int i = 0;
                    PatientInfo pi = new PatientInfo();
                    foreach (var JObject in json["patientInfo"])
                    {
                        if (i % 2 == 0)
                        {
                            pi.patientID = JObject["pId"].ToObject<string>();
                            pi.name = JObject["name"].ToObject<string>();
                            pi.cardNum = JObject["cardNumber"].ToObject<string>();
                            pi.sex = JObject["sex"].ToObject<string>();
                            pi.age = JObject["age"].ToObject<string>();
                            pi.registration_time = JObject["registerTime"].ToObject<string>();
                            pi.visting_time = JObject["arriveTime"].ToObject<string>();
                        }
                        else
                        {
                            pi.height = JObject["height"].ToObject<string>();
                            pi.weight = JObject["weight"].ToObject<string>();
                            pi.temperature = JObject["temperature"].ToObject<string>();
                            pi.respiration = JObject["respiration"].ToObject<string>();
                            pi.pulse = JObject["pulse"].ToObject<string>();
                            pi.blood_pressure = JObject["bloodPressure"].ToObject<string>();
                            pi.disease_description = JObject["description"].ToObject<string>();
                            pi.blood_sugar = JObject["bloodSugar"].ToObject<string>();
                            patients.Add(pi);
                            pi = new PatientInfo();
                        }
                        i++;
                    }
                    patient_view.Source = patients;
                    RegisterPatient.DataContext = patient_view;
                    PatientView.DataContext = patient_view;
                }
                catch
                {
                    MessageBox.Show("无法解析病人数据");
                }
            }
            catch
            {
                MessageBox.Show("服务器错误");
            }

        }
        private void Init_Doctor_Info()
        {

            INITDOCTORINFO initDoctorInfo_ = new INITDOCTORINFO();
            NetworkWorker nw = new NetworkWorker();
            string result = nw.initDoctorInfo(initDoctorInfo_);
            JObject json = (JObject)JsonConvert.DeserializeObject(result);
            try
            {
                //获取医生队列信息
                MessageBox.Show(json["doctor"].ToString());
                JArray array = (JArray)(json["doctor"]);
                foreach (var jObject in array)
                {

                    Doctor_Name.Text = jObject["name"].ToObject<string>();
                    Doctor_Profile.Text = jObject["profile"].ToObject<string>();
                    Doctor_StartTime.Text = jObject["startTime"].ToObject<string>();
                    Doctor_EndTime.Text = jObject["endTime"].ToObject<string>();
                }
                doctor_view.Source = doctors;
                DoctorView.DataContext = doctor_view;
            }
            catch
            {
                MessageBox.Show("无法解析诊室医生信息");
            }
        }
        void sort_queue_by_time(ref ObservableCollection<row> queue)
        {
            int[] selected = new int[queue.Count];
            ObservableCollection<row> temp = new ObservableCollection<row>();
            List<long> list = new List<long>();
            for (int i = 0; i < queue.Count; i++)
            {
                long time_JAVA_Long = long.Parse(queue[i].Time);
                list.Add(time_JAVA_Long);
            }
            list.Sort();

            for (int i = 0; i < queue.Count; i++)
                for (int j = 0; j < queue.Count; j++)
                    if (list[i] == long.Parse(queue[j].Time))
                    {
                        temp.Add(queue[j]);
                    }
            queue = temp;
        }

        private void add_patient_butt_Click(object sender, RoutedEventArgs e)
        {
            add_patient ap = new add_patient();
            ap.Show();
        }

        private void OnListViewItemDoubleClick(object sender, RoutedEventArgs e)
        {


        }
        private void watch(object sender, RoutedEventArgs e)
        {
            if (Waiting_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = waiting[Waiting_queue.SelectedIndex].pId;
                    to.queueId = inTreatId;
                    string result = nw.Send_action(to);

                    SelectedOfficeList_Selected(null, null);

                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }
        }
        private void to_finish(object sender, RoutedEventArgs e)
        {
            if (Watching_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = watching[Watching_queue.SelectedIndex].pId;
                    to.queueId = afterTreatId;
                    string result = nw.Send_action(to);

                    SelectedOfficeList_Selected(null, null);
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }
        }
        private void leave(object sender, RoutedEventArgs e)
        {
            if (Finish_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = finish[Finish_queue.SelectedIndex].pId;
                    to.queueId = "";
                    string result = nw.Send_action(to);

                    SelectedOfficeList_Selected(null, null);
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }
        }
        private void back_to_second(object sender, RoutedEventArgs e)
        {
            if (Watching_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = watching[Watching_queue.SelectedIndex].pId;
                    to.queueId = secTreatId;
                    string result = nw.Send_action(to);
                    refresh();
                    SelectedOfficeList_Selected(null, null);
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }
        }
        private void back_to_triage(object sender, RoutedEventArgs e)
        {
            if (Second_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    ToQueue to = new ToQueue();
                    to.pId = second[Second_queue.SelectedIndex].pId;
                    to.queueId = dispatchTreatId;
                    string result = nw.Send_action(to);
                    refresh();

                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }
        }
        private void SelectedOfficeList_Selected(object sender, RoutedEventArgs e)
        {
            try
            {
                waiting.Clear();
                watching.Clear();
                finish.Clear();

                NetworkWorker nw = new NetworkWorker();
                string result = nw.getClinicDetail(SelectedOfficeList.SelectedItem.ToString());
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                JToken doctorInfo = json["doctorInfo"];
                dId = (string)doctorInfo["dId"];
                Doctor_Name.Text = doctorName = (string)doctorInfo["name"];
                Doctor_Profile.Text = profile = (string)doctorInfo["profile"];
                Doctor_StartTime.Text = startTime = (string)doctorInfo["startTime"];
                Doctor_EndTime.Text = endTime = (string)doctorInfo["endTime"];
                preTreatId = (string)doctorInfo["preTreatId"];
                inTreatId = (string)doctorInfo["inTreatId"];
                afterTreatId = (string)doctorInfo["afterTreatId"];
                JArray array = (JArray)(json["preTreat"]);
                int position = 0;
                foreach (var jObject in array)
                {
                    waiting.Add(new row()
                    {
                        Position = (position++).ToString(),
                        Name = jObject["name"].ToObject<string>(),
                        Time = jObject["arriveTime"].ToObject<string>(),
                        pId = jObject["pId"].ToObject<string>()
                    });
                }
                array = (JArray)(json["inTreat"]);
                position = 0;
                foreach (var jObject in array)
                {
                    watching.Add(new row()
                    {
                        Position = (position++).ToString(),
                        Name = jObject["name"].ToObject<string>(),
                        Time = jObject["arriveTime"].ToObject<string>(),
                        pId = jObject["pId"].ToObject<string>()
                    });
                }
                array = (JArray)(json["afterTreat"]);
                position = 0;
                foreach (var jObject in array)
                {
                    finish.Add(new row()
                    {
                        Position = (position++).ToString(),
                        Name = jObject["name"].ToObject<string>(),
                        Time = jObject["arriveTime"].ToObject<string>(),
                        pId = jObject["pId"].ToObject<string>()
                    });
                }
                waiting_view.Source = waiting;
                Waiting_queue.DataContext = waiting_view;
                watching_view.Source = watching;
                Watching_queue.DataContext = watching_view;
                finish_view.Source = finish;
                Finish_queue.DataContext = finish_view;
            }
            catch
            {
                MessageBox.Show("无法获取诊室队列");
            }
        }
    }
}
