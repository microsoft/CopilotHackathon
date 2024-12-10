import unittest
from metarreader import MetarReader

class TestMetarReader(unittest.TestCase):

    def setUp(self):
        self.reader = MetarReader()
        self.sample_data = [
            "SITE1 RA RMK Remark1",
            "SITE2 RMK Remark2",
            "SITE3"
        ]
        self.reader._add_lines(self.sample_data)

    def test_add_data(self):
        self.reader.add_data('sample_metar.txt')
        self.assertEqual(self.reader.get_number_of_sites(), 3)

    def test_get_number_of_sites(self):
        self.assertEqual(self.reader.get_number_of_sites(), 3)

    def test_site_should_not_exist(self):
        with self.assertRaises(AssertionError):
            self.reader.site_should_not_exist("SITE1")

    def test_site_should_exist(self):
        self.reader.site_should_exist("SITE1")
        with self.assertRaises(AssertionError):
            self.reader.site_should_exist("SITE4")

    def test_it_should_rain(self):
        self.reader.it_should_rain("SITE1")
        with self.assertRaises(AssertionError):
            self.reader.it_should_rain("SITE2")

    def test_get_remarks(self):
        self.assertEqual(self.reader.get_remarks("SITE1"), ["Remark1"])
        self.assertEqual(self.reader.get_remarks("SITE2"), ["Remark2"])
        with self.assertRaises(AssertionError):
            self.reader.get_remarks("SITE3")

if __name__ == '__main__':
    unittest.main()