﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace MyPriorities.Models
{
    public class Login
    {
        public int UserId { get; set; }
        public string UserName { get; set; }
        public int IsExist { get; set; } 
    }
}