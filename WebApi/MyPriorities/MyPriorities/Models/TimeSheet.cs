using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MyPriorities.Models
{
    public class TimeSheet
    {
        public string ProjectName { get; set; }
        public float Sat { get; set; }
        public float Sun{ get; set; }
        public float Mon { get; set; }
        public float Tue { get; set; }
        public float Wed { get; set; }
        public float Thr { get; set; }
        public float Fri { get; set; }
        public int Approved { get; set; }
        public DateTime Weekdate { get; set; }

    }
}