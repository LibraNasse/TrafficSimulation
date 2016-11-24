using System;
using System.Collections;
using System.IO;
namespace SamplePhoneBook
{
	class AddRandomPhone
	{
		string filename;
		string person_name;
		public AddRandomPhone(string fname, string pname)
		{
			filename = fname;
			person_name = pname;
		}

		long generateRandom()
		{
			Random rnd = new Random();
			long rand_number = rnd.Next(300000000, 999999999);
			return rand_number;
		}

		long findRandToHashtable()
		{
			long random = generateRandom();
			HashTabFile hashtabfile = new HashTabFile(filename);
			Hashtable hashtable = hashtabfile.makeHashtable();
			if (hashtable.ContainsValue(random))
			{
				random = generateRandom();
			}

			return random;
		}

		public void addToFile()
		{
			long random_number = findRandToHashtable();
			using (TextWriter w = File.AppendText(filename))
			{
				w.WriteLine(person_name+":"+random_number);
			}
		}
	}
}