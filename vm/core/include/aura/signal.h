/*
 * Copyright (C) 2012 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef AURA_SIGNAL_H
#define AURA_SIGNAL_H

extern jboolean rvmInitSignals(Env* env);
extern jboolean rvmInstallThreadSignalMask(Env* env);
extern jboolean rvmRestoreThreadSignalMask(Env* env);
extern jboolean rvmInstallChainingSignals(Env* env);
extern jboolean rvmReinstallSavedSignals(Env* env, void* state);
extern void* rvmSaveSignals(Env* env);

#endif
