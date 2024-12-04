import { HardhatUserConfig } from "hardhat/config";
import { NetworkUserConfig } from "hardhat/types";
import "@nomicfoundation/hardhat-toolbox";
import path from "path";

import dotenv from 'dotenv';
dotenv.config({ path: path.join(__dirname, '.env') })


const chainIds = {
  arbitrum: 42161,
  avalanche: 43114,
  bsc: 56,
  hardhat: 31337,
  mainnet: 1,
  optimism: 10,
  "polygon-mainnet": 137,
  mumbai: 80001,
  ropsten: 3,
  kovan: 42,
  rinkeby: 4,
  goerli: 5,
  bsctestnet: 97,
  sepolia: 11155111,
};

const deployerPrivateKey: string | undefined = process.env.DEPLOYER_PRIVATE_KEY;
if (!deployerPrivateKey) {
  throw new Error("Please set your DEPLOYER_PRIVATE_KEY in a .env file");
}

const infuraApiKey: string | undefined = process.env.INFURA_API_KEY;
if (!infuraApiKey) {
  throw new Error("Please set your INFURA_API_KEY in a .env file");
}

function getChainConfig(chain: keyof typeof chainIds): NetworkUserConfig {
  let jsonRpcUrl: string;
  switch (chain) {
    case "avalanche":
      jsonRpcUrl = "https://api.avax.network/ext/bc/C/rpc";
      break;
    case "bsc":
      jsonRpcUrl = "https://bsc-dataseed1.binance.org";
      break;
    case "bsctestnet":
      jsonRpcUrl = "https://data-seed-prebsc-1-s1.binance.org:8545";
      break;
    case "mumbai":
      jsonRpcUrl = "https://rpc.ankr.com/polygon_mumbai";
      break;
    case "sepolia":
      jsonRpcUrl = "https://eth-sepolia.public.blastapi.io";
      break;
    default:
      jsonRpcUrl = "https://" + chain + ".infura.io/v3/" + infuraApiKey;
  }

  return {
    accounts: [`0x${deployerPrivateKey}`],
    chainId: chainIds[chain],
    url: jsonRpcUrl,
  };
}


const config: HardhatUserConfig = {
  solidity: "0.8.27",
  networks: {
     hardhat: {
      forking: {
        url: "https://mainnet.infura.io/v3/d5288bc40a1a4a608af11d7b72211975",
        blockNumber: 15536332,
      },
    },
    arbitrum: getChainConfig("arbitrum"),
    avalanche: getChainConfig("avalanche"),
    bsc: getChainConfig("bsc"),
    mainnet: getChainConfig("mainnet"),
    optimism: getChainConfig("optimism"),
    "polygon-mainnet": getChainConfig("polygon-mainnet"),
    mumbai: getChainConfig("mumbai"),
    rinkeby: getChainConfig("rinkeby"),
    goerli: getChainConfig("goerli"),
    kovan: getChainConfig("kovan"),
    ropsten: getChainConfig("ropsten"),
    bsctestnet: getChainConfig("bsctestnet"),
    sepolia: getChainConfig("sepolia"),

  },
  etherscan: {
    apiKey: "ZH1EE27WEWR5GUUNBHIR7WCMNH7EWVFK3D",
  },

};

export default config;
