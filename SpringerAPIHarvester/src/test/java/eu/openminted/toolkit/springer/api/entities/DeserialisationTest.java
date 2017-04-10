package eu.openminted.toolkit.springer.api.entities;

import com.google.gson.Gson;
import org.junit.Test;

/**
 *
 * @author lucasanastasiou
 */
public class DeserialisationTest {

    String exampleResponse = "// 20170406100124\n"
            + "// http://api.springer.com/openaccess/json?api_key=fdcc8721a1359724c6260b04d2ced9b2&q=date%3A2011-11-30&s=61&p=20\n"
            + "\n"
            + "{\n"
            + "  \"query\": \"date:2011-11-30\",\n"
            + "  \"apiKey\": \"fdcc8721a1359724c6260b04d2ced9b2\",\n"
            + "  \"result\": [\n"
            + "    {\n"
            + "      \"total\": \"123\",\n"
            + "      \"start\": \"61\",\n"
            + "      \"pageLength\": \"20\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"records\": [\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2148-11-347\",\n"
            + "      \"title\": \"Integrative analyses of speciation and divergence in (Squamata: Lacertidae)\",\n"
            + "      \"publicationName\": \"BMC Evolutionary Biology\",\n"
            + "      \"issn\": \"1471-2148\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2148-11-347\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"11\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"22\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2148-11-347\",\n"
            + "      \"copyright\": \"©2011 Fitze et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1477-7525-9-107\",\n"
            + "      \"title\": \"Psychometric validation of the Portuguese version of the Neuropathic Pain Symptoms Inventory\",\n"
            + "      \"publicationName\": \"Health and Quality of Life Outcomes\",\n"
            + "      \"issn\": \"1477-7525\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1477-7525-9-107\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"9\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"6\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1477-7525-9-107\",\n"
            + "      \"copyright\": \"©2011 de Andrade et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1476-511X-10-224\",\n"
            + "      \"title\": \"The apolipoprotein A-I mimetic peptide, ETC-642, reduces chronic vascular inflammation in the rabbit\",\n"
            + "      \"publicationName\": \"Lipids in Health and Disease\",\n"
            + "      \"issn\": \"1476-511X\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1476-511X-10-224\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"10\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"8\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1476-511X-10-224\",\n"
            + "      \"copyright\": \"©2011 Di Bartolo et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S3\",\n"
            + "      \"title\": \"Building gene expression profile classifiers with a simple and efficient rejection option in R\",\n"
            + "      \"publicationName\": \"BMC Bioinformatics\",\n"
            + "      \"issn\": \"1471-2105\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2105-12-S13-S3\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"13\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"15\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S3\",\n"
            + "      \"copyright\": \"©2011 Benso et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S14\",\n"
            + "      \"title\": \" secretome analysis approach for next generation sequencing transcriptomic data\",\n"
            + "      \"publicationName\": \"BMC Genomics\",\n"
            + "      \"issn\": \"1471-2164\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2164-12-S3-S14\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"3\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"10\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S14\",\n"
            + "      \"copyright\": \"©2011 Garg and Ranganathan; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1472-6963-11-328\",\n"
            + "      \"title\": \"Voices from the frontline: counsellors' perspectives on TB/HIV collaborative activities in the Northwest Region, Cameroon\",\n"
            + "      \"publicationName\": \"BMC Health Services Research\",\n"
            + "      \"issn\": \"1472-6963\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1472-6963-11-328\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"11\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"12\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1472-6963-11-328\",\n"
            + "      \"copyright\": \"©2011 Njozing et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S15\",\n"
            + "      \"title\": \"Effective gene collection from the metatranscriptome of marine microorganisms\",\n"
            + "      \"publicationName\": \"BMC Genomics\",\n"
            + "      \"issn\": \"1471-2164\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2164-12-S3-S15\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"3\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"8\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S15\",\n"
            + "      \"copyright\": \"©2011 Ogura et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1475-2840-10-108\",\n"
            + "      \"title\": \"Elevated fasting insulin predicts the future incidence of metabolic syndrome: a 5-year follow-up study\",\n"
            + "      \"publicationName\": \"Cardiovascular Diabetology\",\n"
            + "      \"issn\": \"1475-2840\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1475-2840-10-108\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"10\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"7\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1475-2840-10-108\",\n"
            + "      \"copyright\": \"©2011 Sung et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S4\",\n"
            + "      \"title\": \"PTIGS-IdIt, a system for species identification by DNA sequences of the psbA-trnH intergenic spacer region\",\n"
            + "      \"publicationName\": \"BMC Bioinformatics\",\n"
            + "      \"issn\": \"1471-2105\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2105-12-S13-S4\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"13\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"7\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S4\",\n"
            + "      \"copyright\": \"©2011 Liu et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1472-6963-11-329\",\n"
            + "      \"title\": \"Bridging health technology assessment (HTA) with multicriteria decision analyses (MCDA): field testing of the EVIDEM framework for coverage decisions by a public payer in Canada\",\n"
            + "      \"publicationName\": \"BMC Health Services Research\",\n"
            + "      \"issn\": \"1472-6963\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1472-6963-11-329\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"11\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"13\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1472-6963-11-329\",\n"
            + "      \"copyright\": \"©2011 Tony et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S16\",\n"
            + "      \"title\": \"Interrogation of alternative splicing events in duplicated genes during evolution\",\n"
            + "      \"publicationName\": \"BMC Genomics\",\n"
            + "      \"issn\": \"1471-2164\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2164-12-S3-S16\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"3\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"12\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S16\",\n"
            + "      \"copyright\": \"©2011 Chen et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S5\",\n"
            + "      \"title\": \"Prediction of dinucleotide-specific RNA-binding sites in proteins\",\n"
            + "      \"publicationName\": \"BMC Bioinformatics\",\n"
            + "      \"issn\": \"1471-2105\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2105-12-S13-S5\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"13\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"10\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S5\",\n"
            + "      \"copyright\": \"©2011 Fernandez et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1475-9276-10-57\",\n"
            + "      \"title\": \"Changes in the proportion of facility-based deliveries and related maternal health services among the poor in rural Jhang, Pakistan: results from a demand-side financing intervention\",\n"
            + "      \"publicationName\": \"International Journal for Equity in Health\",\n"
            + "      \"issn\": \"1475-9276\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1475-9276-10-57\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"10\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"12\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1475-9276-10-57\",\n"
            + "      \"copyright\": \"©2011 Agha; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1742-4755-8-35\",\n"
            + "      \"title\": \"Mother-to-child transmission of human immunodeficiency virus in aten years period\",\n"
            + "      \"publicationName\": \"Reproductive Health\",\n"
            + "      \"issn\": \"1742-4755\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1742-4755-8-35\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"8\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"10\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1742-4755-8-35\",\n"
            + "      \"copyright\": \"©2011 Delicio et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1742-2094-8-168\",\n"
            + "      \"title\": \"Neuro-inflammation, blood-brain barrier, seizures and autism\",\n"
            + "      \"publicationName\": \"Journal of Neuroinflammation\",\n"
            + "      \"issn\": \"1742-2094\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1742-2094-8-168\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"8\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"5\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1742-2094-8-168\",\n"
            + "      \"copyright\": \"©2011 Theoharides and Zhang; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S18\",\n"
            + "      \"title\": \"Identification of nucleotide patterns enriched in secreted RNAs as putative -acting elements targeting them to exosome nano-vesicles\",\n"
            + "      \"publicationName\": \"BMC Genomics\",\n"
            + "      \"issn\": \"1471-2164\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2164-12-S3-S18\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"3\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"14\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S18\",\n"
            + "      \"copyright\": \"©2011 Batagov et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S7\",\n"
            + "      \"title\": \"Prediction of RNA-binding amino acids from protein and RNA sequences\",\n"
            + "      \"publicationName\": \"BMC Bioinformatics\",\n"
            + "      \"issn\": \"1471-2105\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2105-12-S13-S7\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"13\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"12\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S7\",\n"
            + "      \"copyright\": \"©2011 Choi and Han; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2407-11-505\",\n"
            + "      \"title\": \"Phase I-II study of everolimus and low-dose oral cyclophosphamide in patients with metastatic renal cell cancer\",\n"
            + "      \"publicationName\": \"BMC Cancer\",\n"
            + "      \"issn\": \"1471-2407\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2407-11-505\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"11\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"8\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2407-11-505\",\n"
            + "      \"copyright\": \"©2011 Huijts et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S19\",\n"
            + "      \"title\": \"Assessing the utility of gene co-expression stability in combination with correlation in the analysis of protein-protein interaction networks\",\n"
            + "      \"publicationName\": \"BMC Genomics\",\n"
            + "      \"issn\": \"1471-2164\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1471-2164-12-S3-S19\",\n"
            + "      \"publisher\": \"BioMed Central\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"12\",\n"
            + "      \"number\": \"3\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"11\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S19\",\n"
            + "      \"copyright\": \"©2011 Patil et al; licensee BioMed Central Ltd.\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"identifier\": \"doi:10.1186/1687-1499-2011-189\",\n"
            + "      \"title\": \"Indoor positioning based on statistical multipath channel modeling\",\n"
            + "      \"publicationName\": \"EURASIP Journal on Wireless Communications and Networking\",\n"
            + "      \"issn\": \"1687-1499\",\n"
            + "      \"isbn\": \"\",\n"
            + "      \"doi\": \"10.1186/1687-1499-2011-189\",\n"
            + "      \"publisher\": \"Springer\",\n"
            + "      \"publicationDate\": \"2011-11-30\",\n"
            + "      \"volume\": \"2011\",\n"
            + "      \"number\": \"1\",\n"
            + "      \"startingPage\": \"1\",\n"
            + "      \"endingPage\": \"19\",\n"
            + "      \"url\": \"http://dx.doi.org/10.1186/1687-1499-2011-189\",\n"
            + "      \"copyright\": \"©2011 Yen and Voltz; licensee Springer.\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"facets\": [\n"
            + "    {\n"
            + "      \"name\": \"subject\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"Life Sciences\",\n"
            + "          \"count\": \"60\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Microarrays\",\n"
            + "          \"count\": \"53\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Life Sciences, general\",\n"
            + "          \"count\": \"32\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Medicine & Public Health\",\n"
            + "          \"count\": \"29\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Animal Genetics and Genomics\",\n"
            + "          \"count\": \"28\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Microbial Genetics and Genomics\",\n"
            + "          \"count\": \"27\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Plant Genetics & Genomics\",\n"
            + "          \"count\": \"27\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Proteomics\",\n"
            + "          \"count\": \"27\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Algorithms\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Bioinformatics\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Combinatorial Libraries\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Computational Biology/Bioinformatics\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Computer Appl. in Life Sciences\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Medicine/Public Health, general\",\n"
            + "          \"count\": \"19\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Biomedicine\",\n"
            + "          \"count\": \"17\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Biomedicine general\",\n"
            + "          \"count\": \"12\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Public Health\",\n"
            + "          \"count\": \"11\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Cancer Research\",\n"
            + "          \"count\": \"10\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Oncology\",\n"
            + "          \"count\": \"10\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Surgical Oncology\",\n"
            + "          \"count\": \"10\"\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"name\": \"pub\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"BMC Genomics\",\n"
            + "          \"count\": \"27\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Bioinformatics\",\n"
            + "          \"count\": \"26\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Cancer\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Journal of Medical Case Reports\",\n"
            + "          \"count\": \"5\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Journal of Inequalities and Applications\",\n"
            + "          \"count\": \"4\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Journal of Biomedical Science\",\n"
            + "          \"count\": \"3\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Lipids in Health and Disease\",\n"
            + "          \"count\": \"3\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Biology\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Biotechnology\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Health Services Research\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"BMC Public Health\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Boundary Value Problems\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Breast Cancer Research\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Fixed Point Theory and Applications\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Health Research Policy and Systems\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Journal of High Energy Physics\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Journal of Neuroinflammation\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Veterinary Research\",\n"
            + "          \"count\": \"2\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Advances in Difference Equations\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Annals of Intensive Care\",\n"
            + "          \"count\": \"1\"\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"name\": \"year\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"2011\",\n"
            + "          \"count\": \"123\"\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"name\": \"type\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"Journal\",\n"
            + "          \"count\": \"123\"\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"name\": \"country\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"United States\",\n"
            + "          \"count\": \"25\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Singapore\",\n"
            + "          \"count\": \"15\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Australia\",\n"
            + "          \"count\": \"13\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Japan\",\n"
            + "          \"count\": \"13\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"United Kingdom\",\n"
            + "          \"count\": \"12\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"\",\n"
            + "          \"count\": \"11\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Germany\",\n"
            + "          \"count\": \"9\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Taiwan\",\n"
            + "          \"count\": \"9\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Canada\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"France\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"India\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"South Korea\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"USA\",\n"
            + "          \"count\": \"8\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Malaysia\",\n"
            + "          \"count\": \"7\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"UK\",\n"
            + "          \"count\": \"6\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"China\",\n"
            + "          \"count\": \"5\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Spain\",\n"
            + "          \"count\": \"5\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"The Netherlands\",\n"
            + "          \"count\": \"5\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Brazil\",\n"
            + "          \"count\": \"4\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Denmark\",\n"
            + "          \"count\": \"4\"\n"
            + "        }\n"
            + "      ]\n"
            + "    },\n"
            + "    {\n"
            + "      \"name\": \"keyword\",\n"
            + "      \"values\": [\n"
            + "        {\n"
            + "          \"value\": \"\\n                  \\n                    \\n                  \\n                  \\n                    \\n                      J\\n                    \\n                  \\n                -family of generalized pseudodistances; contractions of Kannan type\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"\\n                APC\\n              \",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"\\n                BRCA1\\n              \",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"abstract convex space\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"abstract convexity\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Adaptive dose finding\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"AdS-CFT Correspondence\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"African origin\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"apolipoproteinA-I\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"apolipoproteinA-I mimetic peptides\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Autism\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Bed design\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Blood-brain barrier\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Blood-Brain Barrier\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"brain\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Breastfeeding\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"cancer predisposition\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"chemosensitivity\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Cholesterol efflux\",\n"
            + "          \"count\": \"1\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"value\": \"Classical Theories of Gravity\",\n"
            + "          \"count\": \"1\"\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}";

    String exampleResponse2 = "// 20170406100905\n" +
"// http://api.springer.com/openaccess/json?api_key=fdcc8721a1359724c6260b04d2ced9b2&q=date%3A2011-11-30&s=81&p=20\n" +
"\n" +
"{\n" +
"  \"query\": \"date:2011-11-30\",\n" +
"  \"apiKey\": \"fdcc8721a1359724c6260b04d2ced9b2\",\n" +
"  \"result\": [\n" +
"    {\n" +
"      \"total\": \"123\",\n" +
"      \"start\": \"81\",\n" +
"      \"pageLength\": \"20\"\n" +
"    }\n" +
"  ],\n" +
"  \"records\": [\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S8\",\n" +
"      \"title\": \"Comprehensive evaluation of matrix factorization methods for the analysis of DNA microarray gene expression data\",\n" +
"      \"publicationName\": \"BMC Bioinformatics\",\n" +
"      \"issn\": \"1471-2105\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2105-12-S13-S8\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"13\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"14\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S8\",\n" +
"      \"copyright\": \"©2011 Kim et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1741-7007-9-83\",\n" +
"      \"title\": \"Notch1 binds and induces degradation of Snail in hepatocellular carcinoma\",\n" +
"      \"publicationName\": \"BMC Biology\",\n" +
"      \"issn\": \"1741-7007\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1741-7007-9-83\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"9\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"12\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1741-7007-9-83\",\n" +
"      \"copyright\": \"©2011 Lim et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1472-6750-11-117\",\n" +
"      \"title\": \"Expression of recombinant multi-coloured fluorescent antibodies in  / cytoplasm\",\n" +
"      \"publicationName\": \"BMC Biotechnology\",\n" +
"      \"issn\": \"1472-6750\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1472-6750-11-117\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"11\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"10\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1472-6750-11-117\",\n" +
"      \"copyright\": \"©2011 Markiv et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S9\",\n" +
"      \"title\": \"HabiSign: a novel approach for comparison of metagenomes and rapid identification of habitat-specific sequences\",\n" +
"      \"publicationName\": \"BMC Bioinformatics\",\n" +
"      \"issn\": \"1471-2105\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2105-12-S13-S9\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"13\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"11\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S9\",\n" +
"      \"copyright\": \"©2011 Ghosh et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S20\",\n" +
"      \"title\": \"Comparative analysis and assessment of  H37Rv protein-protein interaction datasets\",\n" +
"      \"publicationName\": \"BMC Genomics\",\n" +
"      \"issn\": \"1471-2164\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2164-12-S3-S20\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"3\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"19\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S20\",\n" +
"      \"copyright\": \"©2011 Zhou and Wong; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2407-11-501\",\n" +
"      \"title\": \"The breast cancer genome - a key for better oncology\",\n" +
"      \"publicationName\": \"BMC Cancer\",\n" +
"      \"issn\": \"1471-2407\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2407-11-501\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"11\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"6\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2407-11-501\",\n" +
"      \"copyright\": \"©2011 Vollan and Caldas; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2164-12-587\",\n" +
"      \"title\": \"Directional gene expression and antisense transcripts in sexual and asexual stages of \",\n" +
"      \"publicationName\": \"BMC Genomics\",\n" +
"      \"issn\": \"1471-2164\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2164-12-587\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"13\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-587\",\n" +
"      \"copyright\": \"©2011 López-Barragán et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2458-11-900\",\n" +
"      \"title\": \"Socioeconomic status, urbanicity and risk behaviors in Mexican youth: an analysis of three cross-sectional surveys\",\n" +
"      \"publicationName\": \"BMC Public Health\",\n" +
"      \"issn\": \"1471-2458\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2458-11-900\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"11\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"10\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2458-11-900\",\n" +
"      \"copyright\": \"©2011 Gutiérrez and Atienzo; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1423-0127-18-89\",\n" +
"      \"title\": \"Prolonged morphine administration alters protein expression in the rat myocardium\",\n" +
"      \"publicationName\": \"Journal of Biomedical Science\",\n" +
"      \"issn\": \"1423-0127\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1423-0127-18-89\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"18\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"8\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1423-0127-18-89\",\n" +
"      \"copyright\": \"©2011 Drastichova et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S10\",\n" +
"      \"title\": \"Investigation and identification of protein γ-glutamyl carboxylation sites\",\n" +
"      \"publicationName\": \"BMC Bioinformatics\",\n" +
"      \"issn\": \"1471-2105\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2105-12-S13-S10\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"13\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"11\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S10\",\n" +
"      \"copyright\": \"©2011 Lee et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S21\",\n" +
"      \"title\": \"Functional characterization of protein domains common to animal viruses and mouse\",\n" +
"      \"publicationName\": \"BMC Genomics\",\n" +
"      \"issn\": \"1471-2164\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2164-12-S3-S21\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"3\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"10\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S21\",\n" +
"      \"copyright\": \"©2011 Kinjo et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/bcr3068\",\n" +
"      \"title\": \"Advantages of adjuvant chemotherapy for patients with triple-negative breast cancer at Stage II: usefulness of prognostic markers E-cadherin and Ki67\",\n" +
"      \"publicationName\": \"Breast Cancer Research\",\n" +
"      \"issn\": \"1465-5411\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/bcr3068\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"13\",\n" +
"      \"number\": \"6\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"12\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/bcr3068\",\n" +
"      \"copyright\": \"©2011 Kashiwagi et al.; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1476-511X-10-222\",\n" +
"      \"title\": \"13-hydroxy linoleic acid increases expression of the cholesterol transporters ABCA1, ABCG1 and SR-BI and stimulates apoA-I-dependent cholesterol efflux in RAW264.7 macrophages\",\n" +
"      \"publicationName\": \"Lipids in Health and Disease\",\n" +
"      \"issn\": \"1476-511X\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1476-511X-10-222\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"10\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"10\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1476-511X-10-222\",\n" +
"      \"copyright\": \"©2011 Kämmerer et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2407-11-504\",\n" +
"      \"title\": \"Targeting hypoxic tumour cells to overcome metastasis\",\n" +
"      \"publicationName\": \"BMC Cancer\",\n" +
"      \"issn\": \"1471-2407\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2407-11-504\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"11\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"6\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2407-11-504\",\n" +
"      \"copyright\": \"©2011 Bennewith and Dedhar; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1423-0127-18-90\",\n" +
"      \"title\": \"Molecular mechanisms of inflammation and tissue injury after major trauma-is complement the \\\"bad guy\\\"?\",\n" +
"      \"publicationName\": \"Journal of Biomedical Science\",\n" +
"      \"issn\": \"1423-0127\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1423-0127-18-90\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"18\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"16\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1423-0127-18-90\",\n" +
"      \"copyright\": \"©2011 Neher et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S11\",\n" +
"      \"title\": \"Revealing the functionality of hypothetical protein KPN00728 from  e MGH78578: molecular dynamics simulation approaches\",\n" +
"      \"publicationName\": \"BMC Bioinformatics\",\n" +
"      \"issn\": \"1471-2105\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2105-12-S13-S11\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"13\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"14\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S11\",\n" +
"      \"copyright\": \"©2011 Choi et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S22\",\n" +
"      \"title\": \"A comparative structural bioinformatics analysis of inherited mutations in β-D-Mannosidase across multiple species reveals a genotype-phenotype correlation\",\n" +
"      \"publicationName\": \"BMC Genomics\",\n" +
"      \"issn\": \"1471-2164\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2164-12-S3-S22\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"3\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"13\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S22\",\n" +
"      \"copyright\": \"©2011 Huynh et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2105-12-S13-S12\",\n" +
"      \"title\": \"Assessment of predictive models for chlorophyll-a concentration of a tropical lake\",\n" +
"      \"publicationName\": \"BMC Bioinformatics\",\n" +
"      \"issn\": \"1471-2105\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2105-12-S13-S12\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"13\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"11\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2105-12-S13-S12\",\n" +
"      \"copyright\": \"©2011 Malek et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1471-2164-12-S3-S23\",\n" +
"      \"title\": \"Genetic copy number variants in myocardial infarction patients with hyperlipidemia\",\n" +
"      \"publicationName\": \"BMC Genomics\",\n" +
"      \"issn\": \"1471-2164\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1471-2164-12-S3-S23\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"12\",\n" +
"      \"number\": \"3\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"8\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1471-2164-12-S3-S23\",\n" +
"      \"copyright\": \"©2011 Shia et al; licensee BioMed Central Ltd.\"\n" +
"    },\n" +
"    {\n" +
"      \"identifier\": \"doi:10.1186/1755-1536-4-25\",\n" +
"      \"title\": \"Elevation of the antifibrotic peptide -acetyl-seryl-aspartyl-lysyl-proline: a blood pressure-independent beneficial effect of angiotensin I-converting enzyme inhibitors\",\n" +
"      \"publicationName\": \"Fibrogenesis & Tissue Repair\",\n" +
"      \"issn\": \"1755-1536\",\n" +
"      \"isbn\": \"\",\n" +
"      \"doi\": \"10.1186/1755-1536-4-25\",\n" +
"      \"publisher\": \"BioMed Central\",\n" +
"      \"publicationDate\": \"2011-11-30\",\n" +
"      \"volume\": \"4\",\n" +
"      \"number\": \"1\",\n" +
"      \"startingPage\": \"1\",\n" +
"      \"endingPage\": \"12\",\n" +
"      \"url\": \"http://dx.doi.org/10.1186/1755-1536-4-25\",\n" +
"      \"copyright\": \"©2011 Kanasaki et al; licensee BioMed Central Ltd.\"\n" +
"    }\n" +
"  ],\n" +
"  \"facets\": [\n" +
"    {\n" +
"      \"name\": \"subject\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"Life Sciences\",\n" +
"          \"count\": \"60\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Microarrays\",\n" +
"          \"count\": \"53\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Life Sciences, general\",\n" +
"          \"count\": \"32\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Medicine & Public Health\",\n" +
"          \"count\": \"29\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Animal Genetics and Genomics\",\n" +
"          \"count\": \"28\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Microbial Genetics and Genomics\",\n" +
"          \"count\": \"27\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Plant Genetics & Genomics\",\n" +
"          \"count\": \"27\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Proteomics\",\n" +
"          \"count\": \"27\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Algorithms\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Bioinformatics\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Combinatorial Libraries\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Computational Biology/Bioinformatics\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Computer Appl. in Life Sciences\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Medicine/Public Health, general\",\n" +
"          \"count\": \"19\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Biomedicine\",\n" +
"          \"count\": \"17\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Biomedicine general\",\n" +
"          \"count\": \"12\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Public Health\",\n" +
"          \"count\": \"11\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Cancer Research\",\n" +
"          \"count\": \"10\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Oncology\",\n" +
"          \"count\": \"10\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Surgical Oncology\",\n" +
"          \"count\": \"10\"\n" +
"        }\n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"name\": \"pub\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"BMC Genomics\",\n" +
"          \"count\": \"27\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Bioinformatics\",\n" +
"          \"count\": \"26\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Cancer\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Journal of Medical Case Reports\",\n" +
"          \"count\": \"5\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Journal of Inequalities and Applications\",\n" +
"          \"count\": \"4\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Journal of Biomedical Science\",\n" +
"          \"count\": \"3\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Lipids in Health and Disease\",\n" +
"          \"count\": \"3\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Biology\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Biotechnology\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Health Services Research\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"BMC Public Health\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Boundary Value Problems\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Breast Cancer Research\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Fixed Point Theory and Applications\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Health Research Policy and Systems\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Journal of High Energy Physics\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Journal of Neuroinflammation\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Veterinary Research\",\n" +
"          \"count\": \"2\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Advances in Difference Equations\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Annals of Intensive Care\",\n" +
"          \"count\": \"1\"\n" +
"        }\n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"name\": \"year\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"2011\",\n" +
"          \"count\": \"123\"\n" +
"        }\n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"name\": \"type\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"Journal\",\n" +
"          \"count\": \"123\"\n" +
"        }\n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"name\": \"country\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"United States\",\n" +
"          \"count\": \"25\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Singapore\",\n" +
"          \"count\": \"15\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Australia\",\n" +
"          \"count\": \"13\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Japan\",\n" +
"          \"count\": \"13\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"United Kingdom\",\n" +
"          \"count\": \"12\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"\",\n" +
"          \"count\": \"11\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Germany\",\n" +
"          \"count\": \"9\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Taiwan\",\n" +
"          \"count\": \"9\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Canada\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"France\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"India\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"South Korea\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"USA\",\n" +
"          \"count\": \"8\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Malaysia\",\n" +
"          \"count\": \"7\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"UK\",\n" +
"          \"count\": \"6\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"China\",\n" +
"          \"count\": \"5\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Spain\",\n" +
"          \"count\": \"5\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"The Netherlands\",\n" +
"          \"count\": \"5\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Brazil\",\n" +
"          \"count\": \"4\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Denmark\",\n" +
"          \"count\": \"4\"\n" +
"        }\n" +
"      ]\n" +
"    },\n" +
"    {\n" +
"      \"name\": \"keyword\",\n" +
"      \"values\": [\n" +
"        {\n" +
"          \"value\": \"\\n                  \\n                    \\n                  \\n                  \\n                    \\n                      J\\n                    \\n                  \\n                -family of generalized pseudodistances; contractions of Kannan type\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"\\n                APC\\n              \",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"\\n                BRCA1\\n              \",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"abstract convex space\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"abstract convexity\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Adaptive dose finding\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"AdS-CFT Correspondence\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"African origin\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"apolipoproteinA-I\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"apolipoproteinA-I mimetic peptides\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Autism\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Bed design\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Blood-brain barrier\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Blood-Brain Barrier\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"brain\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Breastfeeding\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"cancer predisposition\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"chemosensitivity\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Cholesterol efflux\",\n" +
"          \"count\": \"1\"\n" +
"        },\n" +
"        {\n" +
"          \"value\": \"Classical Theories of Gravity\",\n" +
"          \"count\": \"1\"\n" +
"        }\n" +
"      ]\n" +
"    }\n" +
"  ]\n" +
"}";
    
    @Test
    public void testApiResponseDeserialisation() {
        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(exampleResponse, ApiResponse.class);
        
        System.out.println("a record of the 1st response  = " +response.getRecords().get(0).toString() );
        
        ApiResponse response2 = gson.fromJson(exampleResponse2, ApiResponse.class);
        
        System.out.println("a record of the 2nd response  = " +response2.getRecords().get(0).toString() );
    }
}
