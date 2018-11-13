# -*- coding: utf-8 -*-

# ------------------------------------------------------------------
#  Author : Baruch AMOUSSOU-DJANGBAN
#           Data Scientist 
# ------------------------------------------------------------------

# --> Packages python 

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
from scipy.stats import norm
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import LabelEncoder
from scipy import stats
import warnings
import scipy.stats as stats
from scipy.stats import skew
import matplotlib
import warnings
import scipy
import sklearn
import numpy
import json
import sys
import csv
import os

# ----------
# --> Check package version
# ----------

print('matplotlib: {}'.format(matplotlib.__version__))
print('sklearn: {}'.format(sklearn.__version__))
print('scipy: {}'.format(scipy.__version__))
print('seaborn: {}'.format(sns.__version__))
print('pandas: {}'.format(pd.__version__))
print('numpy: {}'.format(np.__version__))
print('Python: {}'.format(sys.version))

# -----------------------
# Load datasets 
# -----------------------

